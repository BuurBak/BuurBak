package com.buurbak.api.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record PrivateAddressDTO(
        @NotNull
        UUID id,

        @NotBlank
        String city,
        @NotBlank @JsonProperty("street_name")
        String streetName,
        @NotBlank
        String number,
        @NotBlank @JsonProperty("street_name")
        String postalCode
) {
}
