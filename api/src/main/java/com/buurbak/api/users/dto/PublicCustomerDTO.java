package com.buurbak.api.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicCustomerDTO {
        @NotNull
        private UUID id;

        @NotBlank
        private String name;

        @NotNull
        private Collection<PublicRoleDTO> roles;
}
