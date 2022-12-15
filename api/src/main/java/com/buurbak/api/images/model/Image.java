package com.buurbak.api.images.model;

import com.buurbak.api.trailers.model.TrailerOffer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue
    private UUID id;

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private TrailerOffer trailerOffer;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "text")
    private String originalFileName;
    @Column(columnDefinition = "text")
    private String dirName;
    @Column(columnDefinition = "text")
    private String bucketId;


    public Image(String originalFileName, String dirName, String bucketId) {
        this.originalFileName = originalFileName;
        this.dirName = dirName;
        this.bucketId = bucketId;
    }

    public String getPublicUrl() {
        return "https://storage.googleapis.com/" + this.bucketId  + "/" + this.dirName + "/" + this.id + this.getFileExtension();
    }

    public String getFileExtension() {
        return "." + this.originalFileName.split("\\.")[1];
    }
}
