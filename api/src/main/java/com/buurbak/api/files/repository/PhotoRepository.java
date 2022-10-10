package com.buurbak.api.files.repository;

import com.buurbak.api.files.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}
