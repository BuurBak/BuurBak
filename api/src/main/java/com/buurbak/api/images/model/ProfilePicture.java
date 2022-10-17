package com.buurbak.api.images.model;

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
public class ProfilePicture extends Image {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public ProfilePicture(String originalFileName, String location, String dirName, String bucketId, User user) {
        super(originalFileName, location, dirName, bucketId);
        this.user = user;
    }
}
