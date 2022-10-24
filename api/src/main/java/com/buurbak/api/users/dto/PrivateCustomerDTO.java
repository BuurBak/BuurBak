package com.buurbak.api.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivateCustomerDTO extends PublicCustomerDTO {
    @Past
    private LocalDate dateOfBirth;

    @NotBlank
    private String iban;

    @NotBlank
    private String address;

    public PrivateCustomerDTO(@NotNull UUID id, @NotBlank String name, @NotBlank String email, LocalDate dateOfBirth, String iban, String address) {
        super(id, name, email);
        this.dateOfBirth = dateOfBirth;
        this.iban = iban;
        this.address = address;
    }
}
