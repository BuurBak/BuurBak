package com.buurbak.api.files.model;

import com.buurbak.api.security.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Photo {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private File file;
}
