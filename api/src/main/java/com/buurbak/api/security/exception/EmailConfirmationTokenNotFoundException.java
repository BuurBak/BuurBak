package com.buurbak.api.security.exception;

import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;

@NoArgsConstructor
public class EmailConfirmationTokenNotFoundException extends EntityNotFoundException {
    public EmailConfirmationTokenNotFoundException(String message) {
        super(message);
    }
}
