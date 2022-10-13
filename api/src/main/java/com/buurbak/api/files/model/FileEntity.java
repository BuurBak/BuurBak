package com.buurbak.api.files.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    private String name;

    private String contentType;

    private Long size;

    @Lob
    private byte[] data;

    public FileEntity(String name, String contentType, Long size, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }
}
