package com.buurbak.api.reservations.controller;

import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.exception.ReservationAlreadyConfirmedException;
import com.buurbak.api.reservations.exception.ReservationNotFoundException;
import com.buurbak.api.reservations.exception.ReservationRenterIsOwnerException;
import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.reservations.service.ReservationService;
import com.buurbak.api.trailers.exception.TrailerOfferNotFoundException;
import com.buurbak.api.users.exception.CustomerNotFoundException;
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
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("reservations")
public class ReservationController {
    private final ReservationService reservationService;

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
        } catch (TrailerOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer in database", e);
        }
    }

    @DeleteMapping(path = "/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void denyReservation(@PathVariable UUID id) {
        try {
            reservationService.denyReservation(id);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        } catch (TrailerOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer in database", e);
        }
    }

    // Temporary GET requests for Reservation mail system.
    // Needs to be replaced by front end mails, since authentication header is forced to be in query parameter instead of header.
    @PostMapping(path = "/{id}/email/confirm", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String confirmReservationRequest(@PathVariable UUID id, @RequestParam Map<String, String> htmlFormData) {
        System.out.println(htmlFormData);
        return "Reservation confirmed! You can close this page now.";
    }

    @PostMapping(path = "/{id}/email/cancel", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String cancelReservationRequest(@PathVariable UUID id, @RequestParam Map<String, String> htmlFormData) {
        try {
            System.out.println(htmlFormData);
            HttpClient client = HttpClient.newBuilder().build();

            // Set the URI for the request
            URI uri = URI.create("http://localhost:8000/api/v1/reservations/" + id + "/confirm");

            // Create the request
            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Authorization", htmlFormData.get("auth"))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());

            // Print the response status code
            System.out.println(response.statusCode());

            return "Reservation canceled! You can close this page now.";

//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost("http://www.example.com/post");
//            httpPost.setHeader("Content-Type", "application/json");
//            httpPost.setEntity(new StringEntity(""));
//            CloseableHttpResponse response2 = httpClient.execute(httpPost);
//            HttpEntity entity = response2.getEntity();
//            InputStream content = entity.getContent();
//            httpClient.close();
//            response2.close();
//
//            URL url = new URL("http://www.example.com/post");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            OutputStream requestBody = connection.getOutputStream();
//            requestBody.write("{\"key\": \"value\"}".getBytes());
//            requestBody.close();
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.connect();
//            InputStream response3 = connection.getInputStream();
//            connection.disconnect();
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Sending mail action request went wrong, please contact the developers", e);
        }
    }

    @PostMapping(path = "/{id}/email/change-dates", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String changeReservationRequest(@PathVariable UUID id, @RequestParam Map<String, String> htmlFormData) {
        System.out.println(htmlFormData);
        return "Reservation dates changed! You can close this page now.";
    }
}
