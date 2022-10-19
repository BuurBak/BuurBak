package com.buurbak.api.users.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForbiddenException extends RuntimeException {
    private String message;

    public ForbiddenException(String message) {
        super(message);
        this.message = message;
    }
}
