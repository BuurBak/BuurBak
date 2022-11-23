package com.buurbak.api.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class ContactExchangeEmailService {

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.addresses.no-reply-address}")
    private String NOREPLY_ADDRESS;

    public void sendContactExchangeMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(NOREPLY_ADDRESS);
        message.setSubject(subject);
        message.setText(text);
    }
}
