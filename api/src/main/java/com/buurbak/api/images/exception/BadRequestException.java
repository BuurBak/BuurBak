package com.buurbak.api.images.exception;

import lombok.Data;

@Data
@NoArgsConstructor
public class BadRequestException extends RuntimeException{

    private final String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}
