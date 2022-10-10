package com.buurbak.api.files.model;

import com.buurbak.api.security.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ProfilePicture extends Photo {
    @OneToOne
    @Column(nullable = false)
    private User user;
}
