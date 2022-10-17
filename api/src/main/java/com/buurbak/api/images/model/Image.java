package com.buurbak.api.images.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    private UUID id;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    private String originalFileName;
    private String location;
    private String dirName;
    private String bucketId;

    public String getPublicUrl() {
        return "https://storage.googleapis.com/" + this.bucketId  + "/" + this.dirName + "/" + this.id + this.getFileExtension();
    }

    public String getFileExtension() {
        return "." + this.originalFileName.split("\\.")[1];
    }

    public Image(String originalFileName, String location, String dirName, String bucketId) {
        this.id = UUID.randomUUID();
        this.originalFileName = originalFileName;
        this.location = location;
        this.dirName = dirName;
        this.bucketId = bucketId;
    }

    public Image(UUID id, String originalFileName, String location, String dirName, String bucketId) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.location = location;
        this.dirName = dirName;
        this.bucketId = bucketId;
    }
}
