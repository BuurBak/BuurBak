package com.buurbak.api.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PublicRoleDTO {
    @NotBlank
    private String name;

    public PublicRoleDTO(String name) {
        this.name = name;
    }
}
