package com.buurbak.api.users.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    private String name;
    private LocalDate dateOfBirth;
    private String iban;
    private String address;

    @Transient
    private int age;

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public Customer(String email, String password, String name, LocalDate dateOfBirth, String iban, String address) {
        super(email, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.iban = iban;
        this.address = address;
    }
}
