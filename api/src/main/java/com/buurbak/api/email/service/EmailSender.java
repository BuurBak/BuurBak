package com.buurbak.api.email.service;

public interface EmailSender {
    void send(String to, String email);
    String buildEmail(String name, String link);
}
