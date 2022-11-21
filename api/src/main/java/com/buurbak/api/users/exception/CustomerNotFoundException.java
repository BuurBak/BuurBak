package com.buurbak.api.users.exception;

import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;

@NoArgsConstructor
public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
