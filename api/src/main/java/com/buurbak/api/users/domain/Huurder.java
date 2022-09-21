package com.buurbak.api.users.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Huurder extends Person {
    private String iban;
    private String address;

    public Huurder(String name, String email, LocalDate dateOfBirth, String iban, String address) {
        super(name, email, dateOfBirth);
        this.iban = iban;
        this.address = address;
    }
}
