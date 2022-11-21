package com.buurbak.api.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDTO {
        @NotBlank
        private String city;
        @NotBlank @JsonProperty("street_name")
        private String streetName;
        @NotBlank
        private String number;
        @NotBlank @JsonProperty("postal_code")
        private String postalCode;
}
