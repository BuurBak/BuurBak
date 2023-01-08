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
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Customer renter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private TrailerOffer trailer;

    @Column(nullable = false) private LocalDateTime startTime;
    @Column(nullable = false) private LocalDateTime endTime;

    // Object Boolean instead Primitive boolean for possible null value
    // true: confirmed, false: denied/canceled, null: no action taken yet
    private Boolean confirmed;
    private LocalDateTime confirmedAt;
    private LocalDateTime canceledAt;
    private String canceledBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Reservation(Customer renter, TrailerOffer trailer, LocalDateTime startTime, LocalDateTime endTime, Boolean confirmed, LocalDateTime confirmedAt, LocalDateTime canceledAt, String canceledBy) {
        this.renter = renter;
        this.trailer = trailer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmed = confirmed;
        this.confirmedAt = confirmedAt;
        this.canceledAt = canceledAt;
        this.canceledBy = canceledBy;
    }
}
