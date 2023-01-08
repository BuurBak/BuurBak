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

    public void sendRequestMails(UUID reservationId, HttpServletRequest request, String ownerEmail, TrailerOffer trailerOffer, Customer renter, LocalDateTime from, LocalDateTime to, LocalDateTime lastChanged) throws MessagingException {
        emailService.sendHtmlMessage(renter.getEmail(), "Reservation requested", buildEmail(List.of(
                "Reservation for TrailerOffer: " + trailerOffer.getId() + " has been requested from " + from + " to " + to,
                "Please wait for the TrailerOwner to confirm the reservation",
                "or cancel/change the reservation below"
        ), new HashMap<String, String>() {{
            put("Cancel Reservation", "cancel");
        }}, reservationId, request, lastChanged) +
                buildDatesChanger(reservationId, request, from, to, lastChanged));

        emailService.sendHtmlMessage(ownerEmail, "Reservation requested", buildEmail(List.of(
                renter.getName() + " wants to reserve your trailer from " + from + " to " + to,
                "Confirm or deny the request below"
        ), new HashMap<String, String>() {{
            put("Confirm Reservation", "confirm");
            put("Deny Reservation", "cancel");
        }}, reservationId, request, lastChanged));
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

    public void sendDatesChangeMails(UUID reservationId, HttpServletRequest request, String renterEmail, String ownerEmail, LocalDateTime from, LocalDateTime to, LocalDateTime lastChanged) throws MessagingException {
        emailService.sendHtmlMessage(ownerEmail, "Reservation dates changed", buildEmail(List.of(
                "Dates of the Reservation have been changed to:",
                "from " + from + " to " + to + " ",
                "Please wait for the TrailerOwner to confirm the reservation",
                "or cancel/change the reservation below"
        ), new HashMap<String, String>() {{
            put("Cancel Reservation", "cancel?when=before-confirm");
        }}, reservationId, request, lastChanged) +
                buildDatesChanger(reservationId, request, from, to, lastChanged));

        emailService.sendHtmlMessage(renterEmail, "Reservation dates changed", buildEmail(List.of(
                "Dates of the Reservation have been changed to:",
                "from " + from + " to " + to + " ",
                "Confirm or deny the request below"
        ), new HashMap<String, String>() {{
            put("Confirm Reservation", "confirm");
            put("Deny Reservation", "cancel");
        }}, reservationId, request, lastChanged));
    }

    public void sendDeniedMails(String renterEmail, String ownerEmail) throws MessagingException {
        String deniedMail = buildEmail(List.of(
                "Reservation has been denied by the TrailerOwner"
        ));

        emailService.sendHtmlMessage(renterEmail, "Reservation denied", deniedMail);
        emailService.sendHtmlMessage(ownerEmail, "Reservation denied", deniedMail);
    }

    public void sendConfirmedMails(UUID reservationId, HttpServletRequest request, Customer renter, Customer owner, UUID trailerOfferId, LocalDateTime from, LocalDateTime to, LocalDateTime lastChanged) throws MessagingException {
        sendConfirmedMail(reservationId, request, renter, trailerOfferId, from, to, lastChanged);
        sendConfirmedMail(reservationId, request, owner, trailerOfferId, from, to, lastChanged);
    }

    public void sendConfirmedMail(UUID reservationId, HttpServletRequest request, Customer actor, UUID trailerOfferId, LocalDateTime from, LocalDateTime to, LocalDateTime lastChanged) throws MessagingException {
        emailService.sendHtmlMessage(actor.getEmail(), "Reservation confirmed", buildEmail(List.of(
                "Reservation has been confirmed",
                "---",
                "Trailer = " + trailerOfferId,
                "Time frame = " + from + " - " + to,
                "---",
                "Arrange the payment and appointment with the follow contact details:",
                "Name: " + actor.getName(),
                "Email: " + actor.getEmail(),
                "Phone number: " + actor.getNumber(),
                "",
                "If you've changed your mind about the Reservation, you can also cancel it with the link below:"
        ), new HashMap<String, String>() {{
            put("Cancel Reservation", "cancel");
        }}, reservationId, request, lastChanged));
    }

    private String buildEmail(List<String> lines) {
        StringBuilder HtmlMessage = new StringBuilder();
        for (String line : lines) {
            HtmlMessage.append("<p>").append(line).append("</p>");
        }
        return HtmlMessage.toString();
    }

    private String buildEmail(List<String> lines, HashMap<String, String> actions, UUID reservationId, HttpServletRequest request, LocalDateTime lastChanged) {
        StringBuilder HtmlMessage = new StringBuilder();
        HtmlMessage.append(buildEmail(lines));
        HtmlMessage.append("<br><br>");
        actions.forEach((action, endpoint) -> HtmlMessage
                .append("<form action=\"").append(buildLink(reservationId, request, endpoint)).append("\" method=\"post\">")
                .append("  <input type=\"submit\" value=\"").append(action).append("\">")
                .append("  <input type=\"hidden\" name=\"baseLink\" value=\"").append(request.getRequestURL().toString().replace(request.getRequestURI(), "")).append("\">")
                .append("  <input type=\"hidden\" name=\"auth\" value=\"").append(request.getHeader("Authorization")).append("\">")
                .append("  <input type=\"hidden\" name=\"lastChanged\" value=\"").append(lastChanged).append("\">")
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

    private String buildDatesChanger(UUID reservationId, HttpServletRequest request, LocalDateTime from, LocalDateTime to, LocalDateTime lastChanged) {
        return "<form action=\"" + buildLink(reservationId, request, "change-dates") + "\" method=\"post\">" +
                "  <label for=\"startTime\">Start Time: </label>" +
                "  <input type=\"datetime-local\" name=\"startTime\" value=\"" + from + "\" min=\"" + LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)  + "\"><br>" +
                "  <label for=\"endTime\">End Time: </label>" +
                "  <input type=\"datetime-local\" name=\"endTime\" value=\"" + to + "\" min=\"" + LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.MINUTES)  + "\"><br>" +
                "  <input type=\"hidden\" name=\"baseLink\" value=\"" + request.getRequestURL().toString().replace(request.getRequestURI(), "") + "\">" +
                "  <input type=\"hidden\" name=\"auth\" value=\"" + request.getHeader("Authorization") + "\">" +
                "  <input type=\"hidden\" name=\"lastChanged\" value=\"" + lastChanged + "\">" +
                "  <input type=\"submit\" value=\"Change Reservation dates and times\">" +
                "</form>";
    }
}
