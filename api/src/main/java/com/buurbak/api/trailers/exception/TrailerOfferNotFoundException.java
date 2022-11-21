package com.buurbak.api.trailers.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TrailerOfferNotFoundException extends RuntimeException {

    private final String message;

    public TrailerOfferNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
