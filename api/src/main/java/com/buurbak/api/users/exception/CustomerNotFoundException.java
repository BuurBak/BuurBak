package com.buurbak.api.users.exception;

import lombok.*;

import javax.persistence.EntityNotFoundException;

@Getter
@Setter
@NoArgsConstructor
public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
