package com.buurbak.api.users.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public record PrivateCustomerDTO (
        @NotNull
        UUID id,

        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotNull
        Collection<PublicRoleDTO> roles,

        @Past @JsonProperty("date_of_birth")
        LocalDate dateOfBirth,
        @NotBlank
        String iban,
        @NotBlank
        String number,
        @NotNull
        PrivateAddressDTO address
) {
}
