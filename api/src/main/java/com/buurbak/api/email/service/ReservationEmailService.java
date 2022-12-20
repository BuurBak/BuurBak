package com.buurbak.api.email.service;

import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.users.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationEmailService {
    @Autowired
    private EmailService emailService;

    public void sendRequestMails(UUID reservationId, HttpServletRequest request, String ownerEmail, TrailerOffer trailerOffer, Customer renter, LocalDateTime from, LocalDateTime to) throws MessagingException {
        emailService.sendHtmlMessage(renter.getEmail(), "Reservation requested", buildEmail(List.of(
                "Reservation for TrailerOffer: " + trailerOffer.getId() + " has been requested from " + from + " to " + to,
                "Please wait for the TrailerOwner to confirm the reservation",
                "or cancel/change the reservation below"
        ), new HashMap<String, String>() {{
            put("Cancel Reservation", "cancel");
        }}, reservationId, request) +
                buildDatesChanger(reservationId, request, from, to));

        emailService.sendHtmlMessage(ownerEmail, "Reservation requested", buildEmail(List.of(
                renter.getName() + " wants to reserve your trailer from " + from + " to " + to,
                "Confirm or deny the request below"
        ), new HashMap<String, String>() {{
            put("Confirm Reservation", "confirm");
            put("Deny Reservation", "cancel");
        }}, reservationId, request));
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

    public void sendDatesChangeMails(UUID reservationId, HttpServletRequest request, String renterEmail, String ownerEmail, LocalDateTime from, LocalDateTime to) throws MessagingException {
        emailService.sendHtmlMessage(ownerEmail, "Reservation dates changed", buildEmail(List.of(
                "Dates of the Reservation have been changed to:",
                "from " + from + " to " + to + " ",
                "Please wait for the TrailerOwner to confirm the reservation",
                "or cancel/change the reservation below"
        ), new HashMap<String, String>() {{
            put("Cancel Reservation", "cancel");
        }}, reservationId, request) +
                buildDatesChanger(reservationId, request, from, to));

        emailService.sendHtmlMessage(renterEmail, "Reservation dates changed", buildEmail(List.of(
                "Dates of the Reservation have been changed to:",
                "from " + from + " to " + to + " ",
                "Confirm or deny the request below"
        ), new HashMap<String, String>() {{
            put("Confirm Reservation", "confirm");
            put("Deny Reservation", "cancel");
        }}, reservationId, request));
    }

    public void sendDeniedMails(String renterEmail, String ownerEmail) throws MessagingException {
        String deniedMail = buildEmail(List.of(
                "Reservation has been denied by the TrailerOwner"
        ));

        emailService.sendHtmlMessage(renterEmail, "Reservation denied", deniedMail);
        emailService.sendHtmlMessage(ownerEmail, "Reservation denied", deniedMail);
    }

    public void sendConfirmedMails(UUID reservationId, HttpServletRequest request, Customer renter, Customer owner) throws MessagingException {
        emailService.sendHtmlMessage(renter.getEmail(), "Reservation confirmed", buildEmail(List.of(
                "Reservation has been confirmed",
                "Arrange the payment and appointment with the follow contact details:",
                "Name: " + owner.getName(),
                "Email: " + owner.getEmail(),
                "Phone number: " + owner.getNumber(),
                "",
                "If you've changed your mind about the Reservation, you can also cancel it with the link below:"
        ), new HashMap<String, String>() {{
            put("Cancel Reservation", "cancel");
        }}, reservationId, request));

        emailService.sendHtmlMessage(owner.getEmail(), "Reservation confirmed", buildEmail(List.of(
                "Reservation has been confirmed",
                "Arrange the payment and appointment with the follow contact details:",
                "Name: " + renter.getName(),
                "Email: " + renter.getEmail(),
                "Phone number: " + renter.getNumber(),
                "",
                "If you've changed your mind about the Reservation, you can also cancel it with the link below:"
        ), new HashMap<String, String>() {{
            put("Cancel Reservation", "cancel");
        }}, reservationId, request));
    }

    public void sendUnauthorizedErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "Reservation error: action unauthorized", buildEmail(List.of(
                "Error: Unauthorized to perform chosen action.",
                "Please try to log out and log in again."
        )));
    }

    public void sendReservationNotFoundErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "Reservation error", buildEmail(List.of(
                "Error: Reservation was not found",
                "Please contact Buurbak"
        )));
    }

    public void sendDeniedErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "Reservation error", buildEmail(List.of(
                "Error: Reservation was already denied by the TrailerOwner"
        )));
    }

    public void sendRenterCancelledErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "Reservation error", buildEmail(List.of(
                "Error: Reservation was already cancelled by the Renter"
        )));
    }

    public void sendOwnerCancelledErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "Reservation error", buildEmail(List.of(
                "Error: Reservation was already cancelled by the Owner"
        )));
    }

    public void sendDatesErrorMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "Reservation error", buildEmail(List.of(
                "Error: New dates of Reservation can not be in the past",
                "You could try again in the previous email."
        )));
    }

    public void sendOutdatedReservationMail(String email) throws MessagingException {
        emailService.sendHtmlMessage(email, "Reservation error", buildEmail(List.of(
                "Error: Reservation is out of date",
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

    private String buildEmail(List<String> lines, HashMap<String, String> actions, UUID reservationId, HttpServletRequest request) {
        StringBuilder HtmlMessage = new StringBuilder();
        HtmlMessage.append(buildEmail(lines));
        HtmlMessage.append("<br><br>");
        actions.forEach((action, endpoint) -> HtmlMessage
                .append("<form action=\"").append(buildLink(reservationId, request, endpoint)).append("\" method=\"post\">")
                .append("  <input type=\"submit\" value=\"").append(action).append("\">")
                .append("  <input type=\"hidden\" name=\"email\" value=\"").append(request.getUserPrincipal().getName()).append("\">")
                .append("  <input type=\"hidden\" name=\"auth\" value=\"").append(request.getHeader("Authorization")).append("\">")
                .append("</form><br>"));
        return HtmlMessage.toString();
    }

    private String buildLink(UUID reservationId, HttpServletRequest request, String endpoint) {
        return request.getRequestURL().toString().replace(request.getRequestURI(), "") +
                "/api/v1/reservations/" +
                reservationId +
                "/email/" +
                endpoint;
    }

    private String buildDatesChanger(UUID reservationId, HttpServletRequest request, LocalDateTime from, LocalDateTime to) {
        return "<form action=\"" + buildLink(reservationId, request, "change-dates") + "\" method=\"post\">" +
                "  <label for=\"startTime\">Start Time: </label>" +
                "  <input type=\"datetime-local\" name=\"startTime\" value=\"" + from + "\" min=\"" + LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)  + "\"><br>" +
                "  <label for=\"endTime\">End Time: </label>" +
                "  <input type=\"datetime-local\" name=\"endTime\" value=\"" + to + "\" min=\"" + LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.MINUTES)  + "\"><br>" +
                "  <input type=\"hidden\" name=\"auth\" value=\"" + request.getHeader("Authorization") + "\">" +
                "  <input type=\"submit\" value=\"Change Reservation dates and times\">" +
                "</form>";
    }
}
