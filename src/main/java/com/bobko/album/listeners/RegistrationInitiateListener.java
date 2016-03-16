package com.bobko.album.listeners;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.bobko.album.events.OnRegistrationInitiatedEvent;
import com.bobko.album.service.interfaces.IMailService;
import com.bobko.album.service.interfaces.IUserService;

@Component
public class RegistrationInitiateListener implements ApplicationListener<OnRegistrationInitiatedEvent> {

    private static final Logger LOG = Logger.getLogger(RegistrationInitiateListener.class);
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IMailService mailservice;
    
    @Value("${email.username}")
    private String from;
    
    @Override
    public void onApplicationEvent(OnRegistrationInitiatedEvent event) {
        
        try {
            userService.addUser(event.getUser());
        } catch (Exception e) {
            LOG.error("Error during user registration", e);
        }
        
        String confirmUrl = event.getConfirmUrl();
        
        mailservice.sendMail(from, event.getUser().getEmail(), "noreply",
                "Please follow the link to confirm registration " + confirmUrl);
        
    }

}
