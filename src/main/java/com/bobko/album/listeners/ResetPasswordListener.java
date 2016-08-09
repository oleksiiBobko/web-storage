package com.bobko.album.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.bobko.album.events.OnPasswordResetEvent;
import com.bobko.album.service.interfaces.IMailService;
import com.bobko.album.service.interfaces.IUserService;

@Component
public class ResetPasswordListener implements ApplicationListener<OnPasswordResetEvent> {

    private static final Logger LOG = LogManager.getLogger(RegistrationInitiateListener.class);
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IMailService mailservice;
    
    @Value("${email.username}")
    private String from;
    
    @Override
    public void onApplicationEvent(OnPasswordResetEvent event) {
        
        try {
            userService.resetUser(event.getUser());
        } catch (Exception e) {
            LOG.error("Error during user registration", e);
        }
        
        String confirmUrl = event.getConfirmUrl();
        
        mailservice.sendMail(from, event.getUser().getEmail(), "noreply",
                "Please follow the link to set new password " + confirmUrl);
        
    }

}
