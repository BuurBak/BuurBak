package com.buurbak.api.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicCustomerDTO {
    @NotNull
    private UUID id;
    @NotBlank
    private String name;
    @Past
    private LocalDate createdAt;

    private Collection<PublicRoleDTO> roles;
}
