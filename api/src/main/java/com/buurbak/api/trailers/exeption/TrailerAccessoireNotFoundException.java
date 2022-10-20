package com.buurbak.api.trailers.exeption;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Data
@NoArgsConstructor
public class TrailerAccessoireNotFoundException extends EntityNotFoundException {
    private String message;

    public TrailerAccessoireNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}

