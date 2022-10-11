package com.buurbak.api.files.repository;

import com.buurbak.api.files.model.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, UUID> {
}
