package com.buurbak.api.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record CreateAddressDTO(
        @NotBlank
        String city,
        @NotBlank @JsonProperty("street_name")
        String streetName,
        @NotBlank
        String number,
        @NotBlank @JsonProperty("postal_code")
        String postalCode
) {
}
