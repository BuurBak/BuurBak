package com.buurbak.api.email.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

    void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException;
}