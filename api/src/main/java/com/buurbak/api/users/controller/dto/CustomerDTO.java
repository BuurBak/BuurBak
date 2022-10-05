package com.buurbak.api.users.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CustomerDTO {
    @NotNull
    private final UUID id;

    @NotBlank
    private final String email;

    @NotBlank
    private final String name;

    @Past
    private final LocalDate dateOfBirth;

    @NotBlank
    private final String iban;

    @NotBlank
    private final String address;
}
