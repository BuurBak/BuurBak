package com.buurbak.api.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivateAddressDTO {
        @NotNull
        private UUID id;

        @NotBlank
        private String city;
        @NotBlank @JsonProperty("street_name")
        private String streetName;
        @NotBlank
        private String number;
        @NotBlank @JsonProperty("postal_code")
        private String postalCode;
}
