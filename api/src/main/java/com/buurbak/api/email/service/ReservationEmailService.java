package com.buurbak.api.email.service;

import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class ReservationEmailService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private CustomerService customerService;

    public void sendRequestMails(String ownerEmail, TrailerOffer trailerOffer, Customer renter,  LocalDateTime from, LocalDateTime to) throws MessagingException {
        emailService.sendHtmlMessage(renter.getEmail(), "Reservation requested", buildEmail(List.of(
                "Reservation for TrailerOffer: " + trailerOffer + " has been requested from " + from + " to " + to,
                "Please wait for the TrailerOwner to confirm the reservation",
                "or cancel/change the reservation below"
        ), new HashMap<String, String>() {{
            put("Cancel", "https://competenties.hu-open-ict.nl/vaardigheden");
            put("Change dates", "");
        }}));

        emailService.sendHtmlMessage(ownerEmail, "Reservation requested", buildEmail(List.of(
                renter.getName() + " want to reserve your trailer from " + from + " to " + to,
                "Confirm or deny the request below"
        ), new HashMap<String, String>() {{
            put("Confirm", "");
            put("Deny", "");
        }}));
    }

    public void sendRenterCancelMails(String renterEmail, String ownerEmail) throws MessagingException {
        String renterCancelMail = buildEmail(List.of(
                "Reservation request has been cancelled by the Renter"
        ));

        emailService.sendHtmlMessage(renterEmail, "Reservation cancelled", renterCancelMail);
        emailService.sendHtmlMessage(ownerEmail, "Reservation cancelled", renterCancelMail);
    }

    public void sendOwnerCancelMails(String renterEmail, String ownerEmail) throws MessagingException {
        String ownerCancelMail = buildEmail(List.of(
                "Reservation has been cancelled by the TrailerOwner"
        ));

        emailService.sendHtmlMessage(renterEmail, "Reservation cancelled", ownerCancelMail);
        emailService.sendHtmlMessage(ownerEmail, "Reservation cancelled", ownerCancelMail);
    }

    public void sendDatesChangeMails(String renterEmail, String ownerEmail, LocalDateTime from, LocalDateTime to) throws MessagingException {
        emailService.sendHtmlMessage(renterEmail, "Reservation dates changed", buildEmail(List.of(
                "Dates of the Reservation have been changed to:",
                "from " + from + " to " + to + " ",
                "Confirm or deny the request below"
        ), new HashMap<String, String>() {{
            put("Confirm", "");
            put("Deny", "");
        }}));

        emailService.sendHtmlMessage(ownerEmail, "Reservation dates changed", buildEmail(List.of(
                "Dates of the Reservation have been changed to:",
                "from " + from + " to " + to + " ",
                "Please wait for the TrailerOwner to confirm the reservation",
                "or cancel/change the reservation below"
        ), new HashMap<String, String>() {{
            put("Cancel", "");
            put("Change dates", "");
        }}));
    }

    public void sendDeniedMails(String renterEmail, String ownerEmail) throws MessagingException {
        String deniedMail = buildEmail(List.of(
                "Reservation has been denied by the TrailerOwner"
        ));

        emailService.sendHtmlMessage(renterEmail, "Reservation denied", deniedMail);
        emailService.sendHtmlMessage(ownerEmail, "Reservation denied", deniedMail);
    }

    public void sendConfirmedMails(Customer renter, Customer owner) throws MessagingException {
        emailService.sendHtmlMessage(renter.getEmail(), "", buildEmail(List.of(
                "Reservation has been confirmed",
                "Arrange the payment and appointment with the follow contact details:",
                "Name: " + owner.getName(),
                "Email: " + owner.getEmail(),
                "Phone number: " + owner.getNumber(),
                "",
                "If you've changed your mind about the Reservation, you can also cancel it with the link below:"
        ), new HashMap<String, String>() {{
            put("Cancel", "");
        }}));

        emailService.sendHtmlMessage(owner.getEmail(), "", buildEmail(List.of(
                "Reservation has been confirmed",
                "Arrange the payment and appointment with the follow contact details:",
                "Name: " + renter.getName(),
                "Email: " + renter.getEmail(),
                "Phone number: " + renter.getNumber(),
                "",
                "If you've changed your mind about the Reservation, you can also cancel it with the link below:"
        ), new HashMap<String, String>() {{
            put("Cancel", "");
        }}));
    }

    public void sendUnauthorizedErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "", buildEmail(List.of(
                "Error: Unauthorized to perform chosen action.",
                "Please contact Buurbak."
        )));
    }

    public void sendDeniedErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "", buildEmail(List.of(
                "Error: Reservation was denied by the TrailerOwner"
        )));
    }

    public void sendRenterCancelledErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "", buildEmail(List.of(
                "Error: Reservation was cancelled by the Renter"
        )));
    }

    public void sendOwnerCancelledErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "", buildEmail(List.of(
                "Error: Reservation was cancelled by the Owner"
        )));
    }

    public void sendDatesErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "", buildEmail(List.of(
                "New dates of Reservation have to be in the future.",
                "You could try again in the previous email."
        )));
    }

    public void sendOutdatedReservationMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "", buildEmail(List.of(
                "Reservation action didn't go through, because the Reservation was already updated.",
                "Check your inbox for the most recent information of the Reservation."
        )));
    }

    private String buildEmail(List<String> lines) {
        StringBuilder HtmlMessage = new StringBuilder();
        for (String line : lines) {
            HtmlMessage.append("<p>").append(line).append("</p>");
        }
        return HtmlMessage.toString();
    }

    private String buildEmail(List<String> lines, HashMap<String, String> actions) {
        StringBuilder HtmlMessage = new StringBuilder();
        HtmlMessage.append(buildEmail(lines));
        HtmlMessage.append("<br>");
        actions.forEach((action, link) -> HtmlMessage.append("<p><a href=\"").append(link).append("\">").append(action).append("</a></p>"));
        return HtmlMessage.toString();
    }
}
