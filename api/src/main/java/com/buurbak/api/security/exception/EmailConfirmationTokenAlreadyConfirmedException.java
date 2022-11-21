package com.buurbak.api.security.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailConfirmationTokenAlreadyConfirmedException extends RuntimeException {
    public EmailConfirmationTokenAlreadyConfirmedException(String message) {
        super(message);
    }
}
