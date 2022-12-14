package com.buurbak.api.reservations.converter;

import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.dto.ReturnReservationDTO;
import com.buurbak.api.reservations.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationConverter {
    public static Reservation convertReservationDTOtoReservation (ReservationDTO reservationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reservationDTO, Reservation.class);
    }

    public static ReturnReservationDTO convertReservationToReturnReservationDTO (Reservation reservation) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reservation, ReturnReservationDTO.class);
    }
}
