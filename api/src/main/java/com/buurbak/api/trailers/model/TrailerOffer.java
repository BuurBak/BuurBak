package com.buurbak.api.trailers.model;

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

    @Column(nullable = false) private int length;
    @Column(nullable = false) private int height;
    @Column(nullable = false) private int width;
    @Column(nullable = false) private int weight;
    @Column(nullable = false) private int capacity;
    @Column(columnDefinition = "text", nullable = false)  private String licensePlateNumber;
    @Column(nullable = false) private LocalTime pickUpTimeStart;
    @Column(nullable = false) private LocalTime pickUpTimeEnd;
    @Column(nullable = false) private LocalTime dropOffTimeStart;
    @Column(nullable = false) private LocalTime dropOffTimeEnd;
    @Column(columnDefinition = "text", nullable = false) private String location;
    @Column(nullable = false) private double price;
    @Column(nullable = false) private boolean available;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TrailerOffer(TrailerType trailerType, Customer owner, int length, int height,
                        int width, int weight, int capacity, String licensePlateNumber,
                        LocalTime pickUpTimeStart, LocalTime pickUpTimeEnd,
                        LocalTime dropOffTimeStart, LocalTime dropOffTimeEnd,
                        String location, double price, boolean available) {
        this.trailerType = trailerType;
        this.owner = owner;
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
