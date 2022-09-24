package com.buurbak.api.registration.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String name;
    private final String email;
    private final String password;
    private final LocalDate dateOfBirth;
    private final String iban;
    private final String address;
    private final Instant createdAt;
}
