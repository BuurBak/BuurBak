package com.buurbak.api.images.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Data
@NoArgsConstructor
public class ImageNotFoundException extends EntityNotFoundException {
    private String message;

    public ImageNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
