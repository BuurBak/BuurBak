package com.buurbak.api.users.model;

import com.buurbak.api.security.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    @Column(columnDefinition = "text")
    private String name;

    private LocalDate dateOfBirth;

    @Column(columnDefinition = "text")
    private String iban;
    @Column(columnDefinition = "text")
    private String address;

    public Customer(String email, String password, String name, LocalDate dateOfBirth, String iban, String address) {
        super(email, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.iban = iban;
        this.address = address;
    }
}
