package com.buurbak.api.trailers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;
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

    private int length;
    private int height;
    private int width;
    private int weight;
    private int capacity;
    private String licensePlateNumber;
    private LocalTime pickUpTimeStart;
    private LocalTime pickUpTimeEnd;
    private LocalTime dropOffTimeStart;
    private LocalTime dropOffTimeEnd;
    private String location;
    private double price;
    private boolean available;

    @ManyToMany
    private Set<Accessoire> accessoire;


    public TrailerOffer(TrailerType trailerType, int length, int height,
                        int width, int weight, int capacity, String licensePlateNumber,
                        LocalTime pickUpTimeStart, LocalTime pickUpTimeEnd,
                        LocalTime dropOffTimeStart, LocalTime dropOffTimeEnd,
                        String location, double price, boolean available) {
        this.trailerType = trailerType;
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
        this.location = location;
        this.price = price;
        this.available = available;
    }
}
