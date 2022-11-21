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
public class PrivateCustomerDTO extends PublicCustomerDTO {
    @NotBlank
    private String email;

    @Past
    private LocalDate dateOfBirth;

    @NotBlank
    private String iban;

    @NotBlank
    private String address;

    @Past
    private LocalDate updatedAt;
    
    public PrivateCustomerDTO(@NotNull UUID id, @NotBlank String name, @NotBlank String email, @Past LocalDate createdAt, Collection<PublicRoleDTO> roles, LocalDate dateOfBirth, String iban, String address, LocalDate updatedAt) {
        super(id, name, createdAt, roles);
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.iban = iban;
        this.address = address;
        this.updatedAt = updatedAt;
    }
}
