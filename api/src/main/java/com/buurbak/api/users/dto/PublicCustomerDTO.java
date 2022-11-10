package com.buurbak.api.users.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

public record PublicCustomerDTO (
        @NotNull
        UUID id,

        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotNull
        Collection<PublicRoleDTO> roles
) {
}
