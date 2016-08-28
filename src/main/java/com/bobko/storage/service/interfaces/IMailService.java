package com.bobko.storage.service.interfaces;

public interface IMailService {
    public void sendMail(String from, String to, String subject, String body);
}
