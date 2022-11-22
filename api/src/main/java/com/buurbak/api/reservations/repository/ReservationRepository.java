package com.buurbak.api.reservations.repository;

import com.buurbak.api.reservations.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
