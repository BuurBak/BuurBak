package com.buurbak.api.trailers.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EntityNotFoundException;

@Getter
@Setter
@NoArgsConstructor
public class TrailerOfferNotFoundException extends EntityNotFoundException {
    public TrailerOfferNotFoundException(String message) {
        super(message);
    }
}
