package com.buurbak.api.files.service;

import com.buurbak.api.files.model.FileEntity;
import com.buurbak.api.files.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public FileEntity findById(UUID id) throws EntityNotFoundException {
        return fileRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
