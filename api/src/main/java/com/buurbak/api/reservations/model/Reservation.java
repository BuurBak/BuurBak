package com.buurbak.api.reservations.model;

import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.users.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer renter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private TrailerOffer trailer;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private boolean confirmed;

    private LocalDateTime confirmedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Reservation(Customer renter, TrailerOffer trailer, LocalDateTime startTime, LocalDateTime endTime, boolean confirmed) {
        this.renter = renter;
        this.trailer = trailer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmed = confirmed;
    }
}
