package com.buurbak.api.reservations.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReservationAlreadyConfirmedException extends RuntimeException {
    public ReservationAlreadyConfirmedException(String message) {
        super(message);
    }
}
