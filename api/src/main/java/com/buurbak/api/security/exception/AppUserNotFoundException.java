package com.buurbak.api.security.exception;

import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;

@NoArgsConstructor
public class AppUserNotFoundException extends EntityNotFoundException {
    public AppUserNotFoundException(String message) {
        super(message);
    }
}
