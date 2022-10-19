package com.buurbak.api.users.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Data
@NoArgsConstructor
public class CustomerNotFoundException extends EntityNotFoundException {
    private String message;

    public CustomerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
