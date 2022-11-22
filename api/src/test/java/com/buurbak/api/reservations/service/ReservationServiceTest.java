package com.buurbak.api.reservations.service;

import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.reservations.repository.ReservationRepository;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.service.TrailerOfferService;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @Mock ReservationRepository reservationRepository;
    @Mock CustomerService customerService;
    @Mock TrailerOfferService trailerOfferService;
    @InjectMocks ReservationService reservationService;

    @Test
    void shouldGetReservation() {
    }

    @Test
    void shouldAddReservation() {
        EasyRandom easyRandom = new EasyRandom();
        ReservationDTO reservationDTO = easyRandom.nextObject(ReservationDTO.class);
        Customer renter = easyRandom.nextObject(Customer.class);
        TrailerOffer trailer = easyRandom.nextObject(TrailerOffer.class);
        String username = "lucabergman@yahoo.com";

        when(customerService.findByUsername(username)).thenReturn(renter);
        when(trailerOfferService.getTrailerOffer(reservationDTO.getTrailer())).thenReturn(trailer);

        reservationService.addReservation(reservationDTO, username);
        ArgumentCaptor<Reservation> reservationArgumentCaptor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepository).save(reservationArgumentCaptor.capture());
        Reservation capturedReservation = reservationArgumentCaptor.getValue();

        assertThat(capturedReservation).hasFieldOrProperty("id");
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("renter", renter);
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("trailer", trailer);
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("startTime", reservationDTO.getStartTime());
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("endTime", reservationDTO.getEndTime());
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("confirmed", reservationDTO.isConfirmed());
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("confirmedAt", reservationDTO.getConfirmedAt());
        assertThat(capturedReservation).hasFieldOrProperty("createdAt");
        assertThat(capturedReservation).hasFieldOrProperty("updatedAt");
    }

    @Test
    void shouldUpdateReservation() {
        EasyRandom easyRandom = new EasyRandom();
        Reservation reservation = easyRandom.nextObject(Reservation.class);
        ReservationDTO reservationDTO = easyRandom.nextObject(ReservationDTO.class);
        TrailerOffer trailer = easyRandom.nextObject(TrailerOffer.class);

        when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));
        when(trailerOfferService.getTrailerOffer(reservationDTO.getTrailer())).thenReturn(trailer);

        reservationService.updateReservation(reservation.getId(), reservationDTO);
        ArgumentCaptor<Reservation> reservationArgumentCaptor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepository).save(reservationArgumentCaptor.capture());
        Reservation capturedReservation = reservationArgumentCaptor.getValue();

        assertThat(capturedReservation).hasFieldOrProperty("id");
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("renter", reservation.getRenter());
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("trailer", trailer);
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("startTime", reservationDTO.getStartTime());
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("endTime", reservationDTO.getEndTime());
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("confirmed", reservationDTO.isConfirmed());
        assertThat(capturedReservation).hasFieldOrPropertyWithValue("confirmedAt", reservationDTO.getConfirmedAt());
        assertThat(capturedReservation).hasFieldOrProperty("createdAt");
        assertThat(capturedReservation).hasFieldOrProperty("updatedAt");
    }

    @Test
    void shouldDeleteReservation() {
        EasyRandom easyRandom = new EasyRandom();
        Reservation reservation = easyRandom.nextObject(Reservation.class);

        when(reservationRepository.existsById(reservation.getId())).thenReturn(true);

        reservationService.deleteReservation(reservation.getId());
        ArgumentCaptor<UUID> idArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(reservationRepository).deleteById(idArgumentCaptor.capture());

        assertThat(idArgumentCaptor.getValue()).isEqualTo(reservation.getId());
    }

    @Test
    void shouldThrowReservationNotFoundExceptionWhenIdNotFoundWhenDeleteReservation() {

    }
}