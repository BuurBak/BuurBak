package com.buurbak.api.security.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailTakenException extends RuntimeException {
    public EmailTakenException(String message) {
        super(message);
    }
}
