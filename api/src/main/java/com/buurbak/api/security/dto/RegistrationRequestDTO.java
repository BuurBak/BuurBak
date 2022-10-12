package com.buurbak.api.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class RegistrationRequestDTO {
    @NotBlank(message = "Name may not be blank")
    private final String name;

    @NotBlank(message = "Name may not be blank")
    private final String password;

    @NotBlank(message = "Email may not be blank")
    @Email(message = "Email must be valid")
    private final String email;

    @Past(message = "Date of birth must be in the past")
    private final LocalDate dateOfBirth;

    @NotBlank(message = "IBAN may not be blank")
    private final String iban;

    @NotBlank(message = "Address may not be blank")
    private final String address;
}
