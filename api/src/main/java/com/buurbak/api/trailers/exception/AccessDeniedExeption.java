package com.buurbak.api.trailers.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
public class AccessDeniedExeption extends IOException {
    public AccessDeniedExeption(String message) {
        super(message);
    }
}
