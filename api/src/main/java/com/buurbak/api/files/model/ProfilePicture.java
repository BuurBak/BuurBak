package com.buurbak.api.files.model;

import com.buurbak.api.security.model.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePicture extends ImageEntity {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public ProfilePicture(Date createdAt, Date updatedAt, String location, User user) {
        super(createdAt, updatedAt, location);
        this.user = user;
    }
}
