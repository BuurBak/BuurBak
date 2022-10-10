package com.buurbak.api.files.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class File {
    @Id
    @GeneratedValue
    private UUID id;

    private String extension;
    private String location;
}
