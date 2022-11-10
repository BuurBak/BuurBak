package com.buurbak.api.users.dto;

import javax.validation.constraints.NotBlank;

public record PublicRoleDTO (
    @NotBlank
    String name
){
}
