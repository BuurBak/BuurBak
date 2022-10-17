package com.buurbak.api.images.repository;

import com.buurbak.api.images.model.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, UUID> {
}
