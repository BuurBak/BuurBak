package com.buurbak.api.users.model;

import com.buurbak.api.images.model.Image;
import com.buurbak.api.security.model.AppUser;
import com.buurbak.api.security.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends AppUser {
    @Column(columnDefinition = "text")
    private String name;
    private LocalDate dateOfBirth;
    @Column(columnDefinition = "text")
    private String iban;
    @Column(columnDefinition = "text")
    private String number;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Image profilePicture;

    public Customer(String email, String password, String name, LocalDate dateOfBirth, String iban, String number, Address address) {
        super(email, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.iban = iban;
        this.number = number;
        this.address = address;
    }

    public Customer(String email, String password, Collection<Role> roles, String name, LocalDate dateOfBirth, String iban, String number, Address address) {
        super(email, password, roles);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.iban = iban;
        this.number = number;
        this.address = address;
    }
}
