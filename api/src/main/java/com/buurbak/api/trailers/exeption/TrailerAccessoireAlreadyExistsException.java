package com.buurbak.api.trailers.exeption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrailerAccessoireAlreadyExistsException extends RuntimeException{
    private String message;

    public TrailerAccessoireAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}

