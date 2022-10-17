package com.buurbak.api.images.service;

import com.buurbak.api.images.dto.UploadedImageDTO;
import com.buurbak.api.images.exception.*;
import com.buurbak.api.images.model.Image;
import com.buurbak.api.images.repository.ImageRepository;
import com.buurbak.api.images.util.DataBucketUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;
    private final DataBucketUtil dataBucketUtil;

    public Image findById(UUID id) throws ImageNotFoundException {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    public List<Image> uploadImages(MultipartFile[] files) throws NotAnImageException, GCPFileUploadException, BadRequestException, FileWriteException {
        log.info("Saving files to cloud storage and database");

        List<Image> images = new ArrayList<>();

        Arrays.asList(files).forEach(file -> {
            String originalFileName = file.getOriginalFilename();

            if (originalFileName == null) {
                throw new BadOriginalFileNameException();
            }

            Path path = new File(originalFileName).toPath();

            String contentType;

            try {
                contentType = Files.probeContentType(path);
            } catch (IOException e) {
                throw new NotAnImageException("Could not get content type from file");
            }

            UploadedImageDTO uploadedImageDTO = dataBucketUtil.uploadFile(file, originalFileName, contentType);

            if (uploadedImageDTO != null) {
                images.add(new Image(
                        uploadedImageDTO.getId(),
                        originalFileName,
                        uploadedImageDTO.getLocation()
                ));
                log.debug("File uploaded successfully, id: {}, filename: {} and url: {}", uploadedImageDTO.getId(), originalFileName, uploadedImageDTO.getLocation());
            }
        });

        imageRepository.saveAll(images);

        return images;
    }
}
