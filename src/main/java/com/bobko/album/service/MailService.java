package com.bobko.album.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bobko.album.service.interfaces.IMailService;

@Service
public class MailService implements IMailService {

    @Autowired
    private MailSender mailSender;
    
    @Override
    public void sendMail(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
