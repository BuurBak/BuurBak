package com.buurbak.api.users.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicRoleDTO {
    @NotBlank
    private String name;
}
