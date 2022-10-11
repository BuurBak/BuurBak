package com.buurbak.api.files.model;

import com.buurbak.api.security.model.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePicture extends FileEntity {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}
