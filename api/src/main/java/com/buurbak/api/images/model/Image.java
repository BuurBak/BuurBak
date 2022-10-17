package com.buurbak.api.images.model;

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
public class Image {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    private String originalFileName;
    private String location;
    public Image(String originalFileName, String location) {
        this.originalFileName = originalFileName;
        this.location = location;
    }

    public Image(UUID id, String originalFileName, String location) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.location = location;
    }
}
