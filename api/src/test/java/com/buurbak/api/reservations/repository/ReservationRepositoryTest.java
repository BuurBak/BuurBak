package com.buurbak.api.reservations.repository;

import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.trailers.repository.TrailerTypeRepository;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TrailerOfferRepository trailerOfferRepository;
    @Autowired
    private TrailerTypeRepository trailerTypeRepository;

    @AfterEach
    void tearDown() {
        reservationRepository.deleteAll();
        customerRepository.deleteAll();
        trailerOfferRepository.deleteAll();
        trailerTypeRepository.deleteAll();
    }

    @Test
    public void shouldFindAllByTrailerOwnerId() {
        // Given
        EasyRandom easyRandom = new EasyRandom();

        Customer customer = easyRandom.nextObject(Customer.class);
        customer = customerRepository.save(customer);

        TrailerOffer trailerOffer = easyRandom.nextObject(TrailerOffer.class);
        trailerTypeRepository.save(trailerOffer.getTrailerType());
        trailerOffer.setOwner(customer);
        trailerOffer = trailerOfferRepository.save(trailerOffer);

        List<Reservation> reservationList = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Reservation reservation = easyRandom.nextObject(Reservation.class);
            reservation.setRenter(customer);
            reservation.setTrailer(trailerOffer);
            reservationList.add(reservation);
        }

        reservationList = (List<Reservation>) reservationRepository.saveAll(reservationList);

        Pageable pageable = PageRequest.of(0, 20);

        // When
        Page<Reservation> foundReservations = reservationRepository.findAllByTrailerOwnerId(trailerOffer.getOwner().getId(), pageable);

        // Then
        assertFalse(foundReservations.getContent().isEmpty());
        assertEquals(reservationList, foundReservations.getContent());
    }

    @Test
    public void shouldfindNoneByTrailerOwnerId() {
        // Given
        Pageable pageable = PageRequest.of(0, 20);
        UUID id = UUID.randomUUID();

        // When
        Page<Reservation> foundReservations = reservationRepository.findAllByTrailerOwnerId(id, pageable);

        // Then
        assertTrue(foundReservations.getContent().isEmpty());
    }
}
