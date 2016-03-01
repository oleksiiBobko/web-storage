package com.bobko.album.service.interfaces;

public interface IMailService {
    public void sendMail(String from, String to, String subject, String body);
}
