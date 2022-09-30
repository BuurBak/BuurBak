package com.buurbak.api.trailers.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trailer {
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


    public Trailer(TrailerType trailerType, int length, int height , int width, int weight, int capacity, String licensePlateNumber) {
        this.trailerType = trailerType;
        this.length = length;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.capacity = capacity;
        this.licensePlateNumber = licensePlateNumber;
    }
}
