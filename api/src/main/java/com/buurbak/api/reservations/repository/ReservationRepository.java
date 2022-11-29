package com.buurbak.api.reservations.repository;

import com.buurbak.api.reservations.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, UUID> {
    Page<Reservation> findAllByRenterId(UUID renterId, Pageable pageable);




}
