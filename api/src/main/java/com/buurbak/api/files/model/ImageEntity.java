package com.buurbak.api.files.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ImageEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    private String location;

    public ImageEntity(Date createdAt, Date updatedAt, String location) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.location = location;
    }
}
