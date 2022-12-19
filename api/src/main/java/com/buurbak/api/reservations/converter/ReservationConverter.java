package com.buurbak.api.reservations.converter;

import com.buurbak.api.config.ConverterConfig;
import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.model.Reservation;

public class ReservationConverter {
    public static Reservation convertReservationDTOtoReservation (ReservationDTO reservationDTO) {
        return ConverterConfig.getModelMapper().map(reservationDTO, Reservation.class);
    }

    public static ReservationDTO convertReservationToReservationDTO (Reservation reservation) {
        return ConverterConfig.getModelMapper().map(reservation, ReservationDTO.class);
    }
}
