package com.buurbak.api.trailers.model;

import com.buurbak.api.users.model.Address;
import com.buurbak.api.users.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TrailerOffer {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private TrailerType trailerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Customer owner;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    private int length;
    private int height;
    private int width;
    private int weight;
    private int capacity;
    @Column(columnDefinition = "text")
    private String licensePlateNumber;
    private LocalTime pickUpTimeStart;
    private LocalTime pickUpTimeEnd;
    private LocalTime dropOffTimeStart;
    private LocalTime dropOffTimeEnd;
    private double price;
    private boolean available;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TrailerOffer(TrailerType trailerType, Customer owner, Address address, int length, int height,
                        int width, int weight, int capacity, String licensePlateNumber,
                        LocalTime pickUpTimeStart, LocalTime pickUpTimeEnd,
                        LocalTime dropOffTimeStart, LocalTime dropOffTimeEnd,
                        double price, boolean available) {
        this.trailerType = trailerType;
        this.owner = owner;
        this.address = address;
        this.length = length;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.capacity = capacity;
        this.licensePlateNumber = licensePlateNumber;
        this.pickUpTimeStart = pickUpTimeStart;
        this.pickUpTimeEnd = pickUpTimeEnd;
        this.dropOffTimeStart = dropOffTimeStart;
        this.dropOffTimeEnd = dropOffTimeEnd;
        this.price = price;
        this.available = available;
    }
}
