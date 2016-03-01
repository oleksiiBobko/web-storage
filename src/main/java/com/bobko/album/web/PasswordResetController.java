package com.bobko.album.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bobko.album.service.interfaces.IMailService;

@RestController
public class PasswordResetController {
    
    private static final Logger LOG = Logger.getLogger(PasswordResetController.class);
    
    @Autowired
    private IMailService mailservice;
    
    @RequestMapping(value = "/send_reset_password_mail", method = RequestMethod.POST)
    public ResponseEntity<Void> resetPassword(@RequestParam String email) {
        LOG.info(email);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    
}
