package com.buurbak.api.images.repository;

import com.buurbak.api.images.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {}
