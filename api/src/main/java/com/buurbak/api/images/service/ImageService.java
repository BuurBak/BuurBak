package com.buurbak.api.images.service;

import com.buurbak.api.images.config.CgpConfig;
import com.buurbak.api.images.exception.*;
import com.buurbak.api.images.model.Image;
import com.buurbak.api.images.repository.ImageRepository;
import com.buurbak.api.images.util.DataBucketUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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

    private final CgpConfig config;

    public Image findById(UUID id) throws ImageNotFoundException {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    public List<Image> uploadImages(MultipartFile[] files) throws NotAnImageException, GCPFileUploadException, BadRequestException, FileWriteException {
        // This method has two for loops because it is faster to have one call to the database to save multiple entities
        // than to have one for loop and multiple calls to the database.
        log.info("Saving files to database");

        List<Image> images = new ArrayList<>();

        Arrays.asList(files).forEach(file -> {
            String originalFileName = file.getOriginalFilename();

            if (originalFileName == null) {
                throw new BadOriginalFileNameException();
            }

            images.add(new Image(originalFileName, this.config.getDirName(), this.config.getBucketId()));
        });
        imageRepository.saveAll(images);

        log.info("Saving files to google cloud");
        for (int i = 0; i < images.size(); i++) {
            MultipartFile file = Arrays.asList(files).get(i);
            Image image = images.get(i);

            dataBucketUtil.uploadFile(file, image.getId(), image.getOriginalFileName());
        }

        return images;
    }
}
