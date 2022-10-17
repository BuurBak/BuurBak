package com.buurbak.api.files.repository;

import com.buurbak.api.files.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {}
