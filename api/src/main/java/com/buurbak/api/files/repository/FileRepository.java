package com.buurbak.api.files.repository;

import com.buurbak.api.files.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {}
