package com.buurbak.api.reservations.service;

import com.buurbak.api.email.service.ReservationEmailService;
import com.buurbak.api.reservations.converter.ReservationConverter;
import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.exception.*;
import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.reservations.repository.ReservationRepository;
import com.buurbak.api.trailers.exception.TrailerOfferNotFoundException;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.service.TrailerOfferService;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;
    private final TrailerOfferService trailerOfferService;
    private final ReservationEmailService reservationEmailService;

    public Reservation getReservation(UUID id) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
        return reservation;
    }

    public Reservation addReservation(ReservationDTO reservationDTO, String username, HttpServletRequest request) throws CustomerNotFoundException, TrailerOfferNotFoundException, ReservationAlreadyProgressedException, ReservationRenterIsOwnerException, MessagingException {
        Customer customer = customerService.findByUsername(username);
        TrailerOffer trailerOffer = trailerOfferService.getTrailerOffer(reservationDTO.getTrailerId());
        if (reservationRepository.existsByTrailerAndConfirmedTrue(trailerOffer)) throw new ReservationAlreadyProgressedException();
        if (customer == trailerOffer.getOwner()) throw new ReservationRenterIsOwnerException();

        Reservation reservation = ReservationConverter.convertReservationDTOtoReservation(reservationDTO);
        reservation.setRenter(customer);
        reservation.setTrailer(trailerOffer);

        reservationRepository.save(reservation);

        reservationEmailService.sendRequestMails(reservation.getId(), request, trailerOffer.getOwner().getEmail(), trailerOffer, customer, reservation.getStartTime(), reservation.getEndTime(), reservation.getUpdatedAt());

        return reservation;
    }

    public void updateReservation(UUID reservationId, ReservationDTO reservationDTO, LocalDateTime lastChanged, HttpServletRequest request) throws ReservationNotFoundException, ReservationTrailerChangedException, MessagingException {
        Reservation reservation = getReservation(reservationId);
        if (reservationDTO.getTrailerId() == reservation.getTrailer().getId()) throw new ReservationTrailerChangedException();
        if (!lastChanged.truncatedTo(ChronoUnit.SECONDS).equals(reservation.getUpdatedAt().truncatedTo(ChronoUnit.SECONDS))) throw new ReservationAlreadyProgressedException();
        if (Boolean.FALSE.equals(reservation.getConfirmed())) throw new ReservationAlreadyProgressedException();
        if (reservation.getStartTime().isAfter(reservation.getEndTime())) throw new ReservationNegativeDateRangeException();

        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservationRepository.save(reservation);

        reservationEmailService.sendDatesChangeMails(reservationId, request, reservation.getRenter().getEmail(), reservation.getTrailer().getOwner().getEmail(), reservation.getStartTime(), reservation.getEndTime(), reservation.getUpdatedAt());
    }

    public void deleteReservation(UUID reservationId) throws ReservationNotFoundException {
        if(!reservationRepository.existsById(reservationId)) throw new ReservationNotFoundException();

        reservationRepository.deleteById(reservationId);
    }

    public void confirmReservation(UUID id, LocalDateTime lastChanged, HttpServletRequest request) throws ReservationAlreadyProgressedException, MessagingException {
        Reservation reservation = getReservation(id);
        if (!lastChanged.truncatedTo(ChronoUnit.SECONDS).equals(reservation.getUpdatedAt().truncatedTo(ChronoUnit.SECONDS))) throw new ReservationAlreadyProgressedException();
        if (Boolean.FALSE.equals(reservation.getConfirmed())) throw new ReservationAlreadyProgressedException();

        reservation.setConfirmed(true);
        reservation.setConfirmedAt(LocalDateTime.now());
        reservationRepository.save(reservation);

        reservationEmailService.sendConfirmedMails(id, request, reservation.getRenter(), reservation.getTrailer().getOwner(), reservation.getTrailer().getId(), reservation.getStartTime(), reservation.getEndTime(), reservation.getUpdatedAt());
    }

    public void cancelReservation(UUID id, String username, LocalDateTime lastChanged) throws MessagingException {
        Reservation reservation = getReservation(id);

        if (!lastChanged.truncatedTo(ChronoUnit.SECONDS).equals(reservation.getUpdatedAt().truncatedTo(ChronoUnit.SECONDS))) throw new ReservationAlreadyProgressedException();
        if (Boolean.FALSE.equals(reservation.getConfirmed())) throw new ReservationAlreadyProgressedException();

        String renterEmail = reservation.getRenter().getUsername();
        String ownerEmail = reservation.getTrailer().getOwner().getUsername();
        System.out.println(username);
        System.out.println(renterEmail);
        System.out.println(ownerEmail);

        String actor = "";
        if (Objects.equals(username, renterEmail)) actor = "renter";
        if (Objects.equals(username, ownerEmail)) actor = "owner";
        
        if (reservation.getConfirmed() == null) {
            reservationEmailService.sendDeniedMails(renterEmail, ownerEmail);
        } else {
            if (actor.equals("renter")) {
                reservationEmailService.sendRenterCancelMails(renterEmail, ownerEmail);
            } else if (actor.equals("owner")) {
                reservationEmailService.sendOwnerCancelMails(renterEmail, ownerEmail);
            }
        }

        reservation.setConfirmed(false);
        reservation.setCanceledAt(LocalDateTime.now());
        reservation.setCanceledBy(actor);
        reservationRepository.save(reservation);
    }
}
