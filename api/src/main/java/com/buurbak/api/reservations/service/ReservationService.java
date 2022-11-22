package com.buurbak.api.reservations.service;

import com.buurbak.api.reservations.converter.ReservationConverter;
import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.exception.ReservationNotFoundException;
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

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;
    private final TrailerOfferService trailerOfferService;
    private final ReservationConverter reservationConverter;

    public Reservation getReservation(UUID id) throws ReservationNotFoundException {
        return reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
    }

    public Reservation addReservation(ReservationDTO reservationDTO, String username) throws CustomerNotFoundException, TrailerOfferNotFoundException {
        Customer customer = customerService.findByUsername(username);
        TrailerOffer trailerOffer = trailerOfferService.getTrailerOffer(reservationDTO.getTrailer());

        Reservation reservation = reservationConverter.convertReservationDTOtoReservation(reservationDTO);
        reservation.setRenter(customer);
        reservation.setTrailer(trailerOffer);

        reservationRepository.save(reservation);
        return reservation;
    }

    public void updateReservation(UUID reservationId, ReservationDTO reservationDTO) throws ReservationNotFoundException, TrailerOfferNotFoundException {
        Reservation reservation = getReservation(reservationId);
        TrailerOffer trailerOffer = trailerOfferService.getTrailerOffer(reservationDTO.getTrailer());

        Reservation newReservation = reservationConverter.convertReservationDTOtoReservation(reservationDTO);
        newReservation.setId(reservationId);
        newReservation.setTrailer(trailerOffer);
        reservationRepository.save(newReservation);
    }

    public void deleteReservation(UUID reservationId) {
        if(!reservationRepository.existsById(reservationId)) {
            throw new ReservationNotFoundException("Reservation with id " + reservationId + " does not exist");
        }

        reservationRepository.deleteById(reservationId);
        log.info("Reservation with id " + reservationId + " has been deleted");
    }
}
