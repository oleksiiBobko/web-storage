package com.bobko.album.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bobko.album.domain.UserEntity;
import com.bobko.album.events.OnRegistrationInitiatedEvent;
import com.bobko.album.service.interfaces.IUserService;

@RestController
public class MainRestController {
    
    private static final Logger LOG = Logger.getLogger(MainRestController.class);
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher; 
    
    @RequestMapping(value = "/send_reset_password_mail", method = RequestMethod.POST)
    public ResponseEntity<Void> resetPassword(@RequestParam String email) {
        LOG.info(email);
        return new ResponseEntity<>(HttpStatus.OK);
        
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
            return new ResponseEntity<String>("password and confirmation do not equal", HttpStatus.CONFLICT);
        }

        UserEntity checkUser = userService.getUserByName(user.getLogin());
        // check if new user already exists in base
        if (checkUser != null) {
            return new ResponseEntity<String>("nickname already in use", HttpStatus.CONFLICT);
        }
        
        UserEntity emailUser = userService.getUserByEmail(user.getEmail());
        // check if new user already exists in base
        if (emailUser != null) {
            return new ResponseEntity<String>("email already in use", HttpStatus.CONFLICT);
        }
        
        eventPublisher.publishEvent(new OnRegistrationInitiatedEvent(user,
                request.getRequestURL().toString(), request.getRequestURI()));
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
