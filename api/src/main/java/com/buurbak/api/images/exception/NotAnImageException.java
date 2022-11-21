package com.buurbak.api.images.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotAnImageException extends RuntimeException {

    private String message;
    public NotAnImageException(String message) {
        super(message);
        this.message = message;
    }
}
