package com.buurbak.api.email.service;

import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class CancelReservationService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomerService customerService;

    public void sendCancelReservationMail(UUID id) throws MessagingException {
        Customer customer = customerService.getCustomer(id);
        String name = customer.getName();
        String email = customer.getEmail();
        emailService.sendHtmlMessage(email, "Cancel Reservation", buildEmail(name, email));
    }

    private String buildEmail(String name, String email) {
        return "Reservation with id " + "-id-" + " has been cancelled." + "<p>" + "Name: " + name + "<p>" + "Email: " + email;
    }
}
