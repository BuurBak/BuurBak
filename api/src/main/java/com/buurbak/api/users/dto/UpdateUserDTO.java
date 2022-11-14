package com.buurbak.api.users.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserDTO {
    @NotBlank(message = "Email may not be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password may not be blank")
    private String password;
}
