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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.validation.Valid;
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
    public String addReservation(@Valid @RequestBody ReservationDTO reservationDTO, Authentication authentication) {
        try {
            Reservation reservation = reservationService.addReservation(reservationDTO, authentication.getName());
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
    public void confirmReservation(@PathVariable UUID id, @Valid @RequestBody ReservationDTO reservationDTO) {
        try {
            reservationService.confirmReservation(id, reservationDTO);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        } catch (TrailerOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer in database", e);
        }
    }

    @DeleteMapping(path = "/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void denyReservation(@PathVariable UUID id, @Valid @RequestBody ReservationDTO reservationDTO) {
        try {
            reservationService.denyReservation(id, reservationDTO);
        } catch (ReservationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find reservation in database", e);
        } catch (TrailerOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer in database", e);
        }
    }
}
