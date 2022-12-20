package com.buurbak.api.trailers.model;

import com.buurbak.api.users.model.Address;
import com.buurbak.api.randomData.randomizers.TrailerDimensionRandomizer;
import com.buurbak.api.users.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jeasy.random.annotation.Randomizer;

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


    @Column(nullable = false) @Randomizer(TrailerDimensionRandomizer.class)
    private int length;
    @Column(nullable = false)
    private int height;
    @Column(nullable = false) private int width;
    @Column(nullable = false) private int weight;
    @Column(nullable = false) private int capacity;
    @Column(columnDefinition = "text", nullable = false)  private String licensePlateNumber;
    @Column(nullable = false) private LocalTime pickUpTimeStart;
    @Column(nullable = false) private LocalTime pickUpTimeEnd;
    @Column(nullable = false) private LocalTime dropOffTimeStart;
    @Column(nullable = false) private LocalTime dropOffTimeEnd;
    @Column(columnDefinition = "text", nullable = false) private String description;
    @Column(nullable = false) private double price;
    @Column(nullable = false) private boolean available;
    @Column(nullable = false) private double latitude;
    @Column(nullable = false) private double longitude;
    @Column(nullable = false) private double fakeLatitude;
    @Column(nullable = false) private double fakeLongitude;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TrailerOffer(TrailerType trailerType, Customer owner, Address address, int length, int height,
                        int width, int weight, int capacity, String licensePlateNumber, String description,
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
        this.description = description;
        this.price = price;
        this.available = available;
    }
}
