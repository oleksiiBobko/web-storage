package com.bobko.album.web;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bobko.album.domain.UserEntity;
import com.bobko.album.service.interfaces.IMailService;
import com.bobko.album.service.interfaces.IUserService;

@RestController
public class ResetController {
    
    private static final Logger LOG = Logger.getLogger(ResetController.class);
    
    @Autowired
    private IUserService userService;
    
    @RequestMapping(value = "/send_reset_password_mail", method = RequestMethod.POST)
    public ResponseEntity<Void> resetPassword(@RequestParam String email) {
        LOG.info(email);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    
    /**
     * perform adding new user to db
     * */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Void> createNewUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.addUser(user);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
