package com.buurbak.api.users.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class UpdateUserDTO {

    @NotBlank(message = "Name may not be blank")
    private String name;

    @NotBlank(message = "Email may not be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password may not be blank")
    private String password;

    @NotBlank(message = "Address may not be blank")
    private String address;

    @Past(message = "Date of birth must be in the past")
    private final LocalDate dateOfBirth;

    @NotBlank(message = "IBAN may not be blank")
    private final String iban;
}
