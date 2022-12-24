package com.buurbak.api.reservations.controller;

import com.buurbak.api.email.service.ReservationEmailService;
import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.exception.ReservationAlreadyProgressedException;
import com.buurbak.api.reservations.exception.ReservationNotFoundException;
import com.buurbak.api.reservations.exception.ReservationRenterIsOwnerException;
import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.reservations.service.ReservationService;
import com.buurbak.api.trailers.exception.TrailerOfferNotFoundException;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationEmailService reservationEmailService;

    @GetMapping(path = "/{id}")
    public Reservation getReservation(@PathVariable UUID id){
        return reservationService.getReservation(id);
    }

    @Operation(summary = "Add new reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addReservation(@Valid @RequestBody ReservationDTO reservationDTO, Authentication authentication, HttpServletRequest request) {
        try {
            Reservation reservation = reservationService.addReservation(reservationDTO, authentication.getName(), request);
            return reservation.getId().toString();
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find customer in database", e);
        } catch (TrailerOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer in database", e);
        } catch (ReservationAlreadyProgressedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trailer in Reservation already has a confirmed Reservation", e);
        } catch (ReservationRenterIsOwnerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ReservationOwner cannot rent their own Trailer", e);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Email could not be send", e);
        }
    }

    @Operation(summary = "Update reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content)
    })
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReservation(@PathVariable UUID id, @Valid @RequestBody ReservationDTO reservationDTO, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastChanged) {
        try {
            reservationService.updateReservation(id, reservationDTO, lastChanged);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        } catch (TrailerOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer in database", e);
        } catch (ReservationAlreadyProgressedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Reservation has already progressed", e);
        }
    }

    @Operation(summary = "Delete reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrailerOffer(@PathVariable UUID id) {
        try {
            reservationService.deleteReservation(id);
        }
        catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        }

    }

    @PutMapping(path = "/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmReservation(@PathVariable UUID id, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastChanged) {
        try {
            reservationService.confirmReservation(id, lastChanged);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        } catch (ReservationAlreadyProgressedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Reservation has already progressed", e);
        }
    }

    @PutMapping(path = "/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void denyReservation(@PathVariable UUID id, Authentication authentication, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastChanged) {
        try {
            System.out.println(lastChanged);
            reservationService.cancelReservation(id, authentication.getName(), lastChanged);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        } catch (ReservationAlreadyProgressedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Reservation has already progressed", e);
        }
    }

    // Temporary POST requests for Reservation mail system.
    // At first, auth token was sent with html href in GET request query parameter
    // Now, auth token is sent with html form in POST request hidden input field
    // Not completely sure if auth Bearer token in request body is safe enough
    @PostMapping(path = "/{id}/email/confirm", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String confirmReservationRequest(@PathVariable UUID id, @RequestParam Map<String, String> htmlFormData, Authentication authentication) {
        try {
            // Set the URI for the request
            HttpClient client = HttpClient.newBuilder().build();
            URI uri = URI.create(htmlFormData.get("baseLink") + "/api/v1/reservations/" + id + "/confirm?lastChanged=" + htmlFormData.get("lastChanged"));

            // Create and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .uri(uri)
                    .header("Authorization", htmlFormData.get("auth"))
                    .build();

            int statusCode = client.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
            if (statusCode == 204) return "Reservation confirmed! You can close this page now.";
            return returnEmailErrorResponses(statusCode);
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sending mail action went wrong, please contact the developers", e);
        }
    }

    @PostMapping(path = "/{id}/email/cancel", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String cancelReservationRequest(@PathVariable UUID id, @RequestParam Map<String, String> htmlFormData) {
        try {
            // Set the URI for the request
            HttpClient client = HttpClient.newBuilder().build();
            System.out.println(htmlFormData.get("lastChanged"));
            URI uri = URI.create(htmlFormData.get("baseLink") + "/api/v1/reservations/" + id + "/cancel?lastChanged=" + htmlFormData.get("lastChanged"));

            // Create and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .uri(uri)
                    .header("Authorization", htmlFormData.get("auth"))
                    .build();

            System.out.println(request);

            int statusCode = client.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
            if (statusCode == 204) return "Reservation canceled! You can close this page now.";
            return returnEmailErrorResponses(statusCode);
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sending mail action went wrong, please contact the developers", e);
        }
    }

    @PostMapping(path = "/{id}/email/change-dates", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String changeReservationRequest(@PathVariable UUID id, @RequestParam Map<String, String> htmlFormData, Authentication authentication) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            // Set the URI for the request
            HttpClient client = HttpClient.newBuilder().build();
            URI uri = URI.create(htmlFormData.get("baseLink") + "/api/v1/reservations/" + id + "?lastChanged=" + htmlFormData.get("lastChanged"));

            // Create request body
            Reservation reservation = reservationService.getReservation(id);
            ReservationDTO body = new ReservationDTO();
            body.setTrailerId(reservation.getTrailer().getId());
            body.setStartTime(LocalDateTime.parse(htmlFormData.get("startTime")));
            body.setEndTime(LocalDateTime.parse(htmlFormData.get("endTime")));

            // Create and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                    .uri(uri)
                    .header("Authorization", htmlFormData.get("auth"))
                    .header("Content-Type", "application/json")
                    .build();

            int statusCode = client.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
            if (statusCode == 204) return "Reservation dates changed! You can close this page now.";
            return returnEmailErrorResponses(statusCode);
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sending mail action went wrong, please contact the developers", e);
        }
    }

    String returnEmailErrorResponses(int statusCode) {
        System.out.println(statusCode);
        if (statusCode == 401) {
            return "Error: Unauthorized to perform chosen action. Please contact Buurbak.";
        }else if (statusCode == 403) {
            return "Error: Reservation has already proceeded to the next step or has been changed. Please check your inbox for more recent information.";
        } else if (statusCode == 404) {
            return "Error: Reservation was not found. Please contact Buurbak";
        }
        return "Something went wrong, check your inbox or contact Buurbak.";
    };
}
