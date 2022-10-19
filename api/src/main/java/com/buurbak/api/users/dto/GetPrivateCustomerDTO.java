package com.buurbak.api.users.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class GetPrivateCustomerDTO {
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

    @Size(min = 1)
    private final String profilePictureUrl;
}