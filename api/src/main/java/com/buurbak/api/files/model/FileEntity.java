package com.buurbak.api.files.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class FileEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @GeneratedValue
    private Date created_at;

    private String name;

    private String contentType;

    private Long size;

    @Lob
    private byte[] data;
}
