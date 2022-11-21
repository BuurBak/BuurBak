package com.buurbak.api.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition = "text")
    private String city;
    @Column(columnDefinition = "text")
    private String streetName;
    @Column(columnDefinition = "text")
    private String number;
    @Column(columnDefinition = "text")
    private String postalCode;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Address(String city, String streetName, String number, String postalCode) {
        this.city = city;
        this.streetName = streetName;
        this.number = number;
        this.postalCode = postalCode;
    }
}
