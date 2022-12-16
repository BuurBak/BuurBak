package com.buurbak.api.reservations.repository;

import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.trailers.repository.TrailerTypeRepository;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ReservationRepositoryTest {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TrailerOfferRepository trailerOfferRepository;
    @Autowired
    TrailerTypeRepository trailerTypeRepository;

    EasyRandom easyRandom = new EasyRandom();
    Customer givenRenter;
    Customer givenOwner;
    TrailerOffer givenTrailer;

    @BeforeEach
    void setUp() {
        givenRenter = customerRepository.save(easyRandom.nextObject(Customer.class));
        givenOwner = customerRepository.save(easyRandom.nextObject(Customer.class));

        TrailerType trailerType = easyRandom.nextObject(TrailerType.class);
        trailerTypeRepository.save(trailerType);

        TrailerOffer randomTrailer = easyRandom.nextObject(TrailerOffer.class);
        randomTrailer.setTrailerType(trailerType);
        randomTrailer.setOwner(givenOwner);
        givenTrailer = trailerOfferRepository.save(randomTrailer);
    }

    @AfterEach
    void tearDown() {
        reservationRepository.deleteAll();
        customerRepository.deleteAll();
        trailerOfferRepository.deleteAll();
        trailerTypeRepository.deleteAll();
    }

    @Test
    void shouldNotHaveReservedTrailerWhenNoReservation() {
        // Given
        Reservation randomReservation = easyRandom.nextObject(Reservation.class);
        randomReservation.setRenter(givenRenter);
        randomReservation.setTrailer(givenTrailer);
        reservationRepository.save(randomReservation);

        // When
        TrailerOffer randomTrailer = easyRandom.nextObject(TrailerOffer.class);
        randomTrailer.setOwner(givenOwner);
        boolean reservationExists = reservationRepository.existsByTrailerAndConfirmedTrue(randomTrailer);

        // Then
        assertFalse(reservationExists);
    }

    @Test
    void shouldNotHaveReservedTrailerWhenUnconfirmedReservation() {
        // Given
        Reservation givenUnconfirmedReservation = easyRandom.nextObject(Reservation.class);
        givenUnconfirmedReservation.setRenter(givenRenter);
        givenUnconfirmedReservation.setTrailer(givenTrailer);
        givenUnconfirmedReservation.setConfirmed(false);
        reservationRepository.save(givenUnconfirmedReservation);

        // When
        boolean reservationExists = reservationRepository.existsByTrailerAndConfirmedTrue(givenTrailer);

        // Then
        assertFalse(reservationExists);
    }

    @Test
    void shouldHaveReservedTrailerWhenConfirmedReservation() {
        // Given
        Reservation givenConfirmedReservation = easyRandom.nextObject(Reservation.class);
        givenConfirmedReservation.setRenter(givenRenter);
        givenConfirmedReservation.setTrailer(givenTrailer);
        givenConfirmedReservation.setConfirmed(true);
        reservationRepository.save(givenConfirmedReservation);

        // When
        boolean reservationExists = reservationRepository.existsByTrailerAndConfirmedTrue(givenTrailer);

        // Then
        assertTrue(reservationExists);
    }
}