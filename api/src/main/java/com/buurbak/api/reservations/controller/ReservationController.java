package com.buurbak.api.reservations.controller;

import com.buurbak.api.email.service.ReservationEmailService;
import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.exception.ReservationAlreadyConfirmedException;
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
        } catch (ReservationAlreadyConfirmedException e) {
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
    public void updateReservation(@PathVariable UUID id, @Valid @RequestBody ReservationDTO reservationDTO) {
        try {
            reservationService.updateReservation(id, reservationDTO);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        } catch (TrailerOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer in database", e);
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

    @PostMapping(path = "/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmReservation(@PathVariable UUID id) {
        try {
            reservationService.confirmReservation(id);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        }
    }

    @DeleteMapping(path = "/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void denyReservation(@PathVariable UUID id) {
        try {
            reservationService.denyReservation(id);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
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
            URI uri = URI.create("http://localhost:8000/api/v1/reservations/" + id + "/confirm");

            // Create and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .uri(uri)
                    .header("Authorization", htmlFormData.get("auth"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 204) {
                if (response.statusCode() == 401) {
                    reservationEmailService.sendUnauthorizedErrorMail(htmlFormData.get("email"));
                }
                return "Something went wrong, check your inbox or contact Buurbak.";
            }

            return "Reservation confirmed! You can close this page now.";
        } catch (IOException | InterruptedException | MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sending mail action went wrong, please contact the developers", e);
        }
    }

    @PostMapping(path = "/{id}/email/cancel", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String cancelReservationRequest(@PathVariable UUID id, @RequestParam Map<String, String> htmlFormData) {
        try {
            // Set the URI for the request
            HttpClient client = HttpClient.newBuilder().build();
            URI uri = URI.create("http://localhost:8000/api/v1/reservations/" + id + "/confirm");

            // Create and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .DELETE()
                    .uri(uri)
                    .header("Authorization", htmlFormData.get("auth"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 204) {
                if (response.statusCode() == 401) {
                    reservationEmailService.sendUnauthorizedErrorMail(htmlFormData.get("email"));
                } else if (response.statusCode() == 404) {
                    reservationEmailService.sendReservationNotFoundErrorMail(htmlFormData.get("email"));
                } else if (response.statusCode() == 4)
                return "Something went wrong, check your inbox or contact Buurbak.";
            }

            return "Reservation canceled! You can close this page now.";
        } catch (IOException | InterruptedException | MessagingException e) {
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
            URI uri = URI.create("http://localhost:8000/api/v1/reservations/" + id);

            // Create request body
            Reservation reservation = reservationService.getReservation(id);
            ReservationDTO body = new ReservationDTO();
            body.setStartTime(LocalDateTime.parse(htmlFormData.get("startTime")));
            body.setEndTime(LocalDateTime.parse(htmlFormData.get("endTime")));
            body.setTrailerId(reservation.getTrailer().getId());
            body.setConfirmed(reservation.isConfirmed());

            // Create and send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(body)))
                    .uri(uri)
                    .header("Authorization", htmlFormData.get("auth"))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 204) {
                if (response.statusCode() == 404) {
                    reservationEmailService.sendUnauthorizedErrorMail(htmlFormData.get("email"));
                }
                return "Something went wrong, check your inbox or contact Buurbak.";
            }

            return "Reservation dates changed! You can close this page now.";
        } catch (IOException | InterruptedException | MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sending mail action went wrong, please contact the developers", e);
        }
    }
}
