package com.buurbak.api.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RegisterNewCustomerDTO(
    @NotBlank @Email
    String email,
    @NotBlank
    String password,
    @NotBlank
    String name,
    @NotBlank
    String number,
    @NotNull
    CreateAddressDTO address
) {
}
