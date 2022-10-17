package com.buurbak.api.images.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadOriginalFileNameException extends RuntimeException {
    private String message;

    public BadOriginalFileNameException(String message) {
        super(message);
        this.message = message;
    }
}
