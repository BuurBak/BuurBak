package com.buurbak.api.reservations.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EntityNotFoundException;

@Getter
@Setter
@NoArgsConstructor
public class ReservationNotFoundException extends EntityNotFoundException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}
