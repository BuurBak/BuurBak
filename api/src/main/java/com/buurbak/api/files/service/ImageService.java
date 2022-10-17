package com.buurbak.api.files.service;

import com.buurbak.api.files.exception.ImageNotFoundException;
import com.buurbak.api.files.model.ImageEntity;
import com.buurbak.api.files.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageEntity findById(UUID id) throws ImageNotFoundException {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }
}
