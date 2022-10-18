package com.buurbak.api.images.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @Lob
    private String originalFileName;
    @Lob
    private String dirName;
    @Lob
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
