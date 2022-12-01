package com.buurbak.api.reservations.converter;

import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.model.Reservation;
import org.modelmapper.ModelMapper;

public class ReservationConverter {
    public static Reservation convertReservationDTOtoReservation (ReservationDTO reservationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reservationDTO, Reservation.class);
    }

    public static ReservationDTO convertReservationToReservationDTO (Reservation reservation) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reservation, ReservationDTO.class);
    }
}
