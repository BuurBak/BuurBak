package com.buurbak.api.users.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivateCustomerDTO {
        @NotNull
        private UUID id;

        @NotBlank
        private String name;
        @NotBlank
        private String email;
        @NotNull
        private Collection<PublicRoleDTO> roles;

        @Past @JsonProperty("date_of_birth")
        private LocalDate dateOfBirth;
        @NotBlank
        private String iban;
        @NotBlank
        private String number;
        @Valid
        private PrivateAddressDTO address;
}
