package com.buurbak.api.images.model;

import com.buurbak.api.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Polymorphism(type = PolymorphismType.IMPLICIT)
@Getter
@Setter
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

    @Column(columnDefinition = "text")
    private String originalFileName;
    @Column(columnDefinition = "text")
    private String dirName;
    @Column(columnDefinition = "text")
    private String bucketId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

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
