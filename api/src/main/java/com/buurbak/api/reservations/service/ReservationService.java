package com.buurbak.api.reservations.service;

import com.buurbak.api.email.service.ReservationEmailService;
import com.buurbak.api.reservations.converter.ReservationConverter;
import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.exception.ReservationAlreadyConfirmedException;
import com.buurbak.api.reservations.exception.ReservationNotFoundException;
import com.buurbak.api.reservations.exception.ReservationRenterIsOwnerException;
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

    public Reservation addReservation(ReservationDTO reservationDTO, String username, HttpServletRequest request) throws CustomerNotFoundException, TrailerOfferNotFoundException, ReservationAlreadyConfirmedException, ReservationRenterIsOwnerException, MessagingException {
        Customer customer = customerService.findByUsername(username);
        TrailerOffer trailerOffer = trailerOfferService.getTrailerOffer(reservationDTO.getTrailerId());
        if (reservationRepository.existsByTrailerAndConfirmedTrue(trailerOffer))
            throw new ReservationAlreadyConfirmedException();
        if (customer == trailerOffer.getOwner()) throw new ReservationRenterIsOwnerException();

        Reservation reservation = ReservationConverter.convertReservationDTOtoReservation(reservationDTO);
        reservation.setRenter(customer);
        reservation.setTrailer(trailerOffer);
        reservation.setCreatedAt(reservation.getCreatedAt());

        reservationRepository.save(reservation);

        reservationEmailService.sendRequestMails(reservation.getId(), request, trailerOffer.getOwner().getEmail(), trailerOffer, customer, reservation.getStartTime(), reservation.getEndTime());

        return reservation;
    }

    public void updateReservation(UUID reservationId, ReservationDTO reservationDTO) throws ReservationNotFoundException, TrailerOfferNotFoundException {
        Customer renter = getReservation(reservationId).getRenter();
        TrailerOffer trailerOffer = trailerOfferService.getTrailerOffer(reservationDTO.getTrailerId());

        Reservation newReservation = ReservationConverter.convertReservationDTOtoReservation(reservationDTO);
        newReservation.setId(reservationId);
        newReservation.setRenter(renter);
        newReservation.setTrailer(trailerOffer);
        reservationRepository.save(newReservation);
    }

    public void deleteReservation(UUID reservationId) {
        if(!reservationRepository.existsById(reservationId)) throw new ReservationNotFoundException();

        reservationRepository.deleteById(reservationId);
    }

    public void confirmReservation(UUID id) {
//        Reservation reservation = getReservation(id);

    }

    public void denyReservation(UUID id) {
    }
}
