package com.buurbak.api.security.exception;

import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;

@NoArgsConstructor
public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}