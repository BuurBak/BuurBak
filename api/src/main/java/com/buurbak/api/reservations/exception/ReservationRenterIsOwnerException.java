package com.buurbak.api.reservations.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReservationRenterIsOwnerException extends RuntimeException {
    public ReservationRenterIsOwnerException(String message) {
        super(message);
    }
}
