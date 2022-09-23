package com.buurbak.api.users.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends Person {

    @NotBlank(message = "Must include an iban")
    private String iban;
    @NotBlank(message = "Must include an address")
    private String address;

    public Customer(String name, String email, LocalDate dateOfBirth, String iban, String address) {
        super(name, email, dateOfBirth);
        this.iban = iban;
        this.address = address;
    }
}
