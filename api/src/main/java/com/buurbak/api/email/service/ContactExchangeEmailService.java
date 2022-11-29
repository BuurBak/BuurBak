package com.buurbak.api.email.service;

import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class ContactExchangeEmailService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private CustomerService customerService;

    @Value("${spring.mail.addresses.no-reply-address}")
    private String NOREPLY_ADDRESS;

    public void sendContactExchangeMail(UUID id) throws MessagingException {
        Customer customer = customerService.getCustomer(id);
        String name = customer.getName();
        String email = customer.getEmail();
        String number = customer.getNumber();
        emailService.sendHtmlMessage(email, "Contact details", buildEmail1(name, email, number));
    }

    private String buildEmail1(String name, String email, String number) {
        return "Name: " + name + "<p>" + "Email: " + email + "<p>" + "Phone number: " + number;
    }

//    public void sendContactExchangeMail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(NOREPLY_ADDRESS);
//        message.setSubject(subject);
//        message.setText(text);
//    }
}
