package com.buurbak.api.files.model;

import com.buurbak.api.security.model.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Data
public class ProfilePicture extends FileEntity{
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
}
