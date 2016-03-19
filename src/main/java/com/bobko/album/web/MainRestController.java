package com.bobko.album.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bobko.album.domain.UserEntity;
import com.bobko.album.events.OnPasswordResetEvent;
import com.bobko.album.events.OnRegistrationInitiatedEvent;
import com.bobko.album.exceptions.UserActivationException;
import com.bobko.album.service.interfaces.IUserService;

@RestController
public class MainRestController {
    
    private static final Logger LOG = Logger.getLogger(MainRestController.class);
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher; 
    
    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public ResponseEntity<String> resetPassword(@RequestParam String email,
            HttpServletRequest request) {
        UserEntity checkUser = userService.getUserByName(email);
        // try to fetch user by nickname
        if (checkUser == null) {
            checkUser = userService.getUserByEmail(email);
            if(checkUser == null) {
                return new ResponseEntity<String>("no such user", HttpStatus.NOT_FOUND);
            }
        }
        
        eventPublisher.publishEvent(new OnPasswordResetEvent(checkUser,
                request.getRequestURL().toString()));
        return new ResponseEntity<>("Check your Email", HttpStatus.OK);
        
    }
    
    /**
     * perform adding new user to db
     * */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> createNewUser(
            @Valid @ModelAttribute("user") UserEntity user,
            BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        if (!user.getPw().equals(user.getPwConfirmation())) {
            return new ResponseEntity<String>("Password and confirmation are not equal", HttpStatus.CONFLICT);
        }

        UserEntity checkUser = userService.getUserByName(user.getLogin());
        // check if new user already exists in base
        if (checkUser != null) {
            return new ResponseEntity<String>("Nickname already in use", HttpStatus.CONFLICT);
        }
        
        UserEntity emailUser = userService.getUserByEmail(user.getEmail());
        // check if new user already exists in base
        if (emailUser != null) {
            return new ResponseEntity<String>("Email already in use", HttpStatus.CONFLICT);
        }
        
        eventPublisher.publishEvent(new OnRegistrationInitiatedEvent(user,
                request.getRequestURL().toString()));
        
        return new ResponseEntity<>("Account created", HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/save_password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('PASSWORD_UPDATE')")
    @ResponseBody
    public ResponseEntity<String> savePassword(
            @ModelAttribute("user") UserEntity user,
            HttpServletRequest request, HttpServletResponse response) {
        
        if (user.getPw() == null || user.getPw().isEmpty()
                || !user.getPw().equals(user.getPwConfirmation())) {
            return new ResponseEntity<String>("password can't be empty", HttpStatus.CONFLICT);
        }
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        UserEntity oldUser = null;
        
        if(principal instanceof UserEntity) {
            oldUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return new ResponseEntity<String>("Can't update password", HttpStatus.CONFLICT);
        }
        
        try {
            userService.changeUserPassword(oldUser, user.getPw());
        } catch (UserActivationException e) {
            return new ResponseEntity<String>("Can't update password", HttpStatus.CONFLICT);
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
           new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        
    }
    
}
