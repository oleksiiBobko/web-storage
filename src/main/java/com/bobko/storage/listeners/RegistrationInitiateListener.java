package com.bobko.storage.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.bobko.storage.events.OnRegistrationInitiatedEvent;
import com.bobko.storage.service.interfaces.IMailService;
import com.bobko.storage.service.interfaces.IUserService;

@Component
public class RegistrationInitiateListener implements ApplicationListener<OnRegistrationInitiatedEvent> {

	private static final Logger LOG = LogManager.getLogger(RegistrationInitiateListener.class);
    
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
            String confirmUrl = event.getConfirmUrl();
            mailservice.sendMail(from, event.getUser().getEmail(), "noreply",
                    "Please follow the link to confirm registration " + confirmUrl);
        } catch (Exception e) {
            LOG.error("Error during user registration", e);
        }
        
    }

}
