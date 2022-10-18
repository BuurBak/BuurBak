package com.buurbak.api.images.util;

import com.buurbak.api.images.config.CgpConfig;
import com.buurbak.api.images.dto.UploadedImageDTO;
import com.buurbak.api.images.exception.BadRequestException;
import com.buurbak.api.images.exception.FileWriteException;
import com.buurbak.api.images.exception.GCPFileUploadException;
import com.buurbak.api.images.exception.NotAnImageException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Component
@Slf4j
@RequiredArgsConstructor
public class DataBucketUtil {
    private final String[] EXTENSION_LIST = {".png", ".jpeg"};

    @Autowired
    private final CgpConfig config;

    public UploadedImageDTO uploadFile(MultipartFile multipartFile, String fileName, String contentType) throws GCPFileUploadException, NotAnImageException, BadRequestException, FileWriteException {
        try {
            log.debug("Start file uploading process on GCS");
            String fileExtension = checkFileExtension(fileName);

            byte[] fileData = multipartFile.getBytes();
            UUID id = UUID.randomUUID();

            Bucket bucket = getBucket();
            Blob blob = bucket.create(config.getDirName() + "/" + id + fileExtension, fileData, contentType);

            log.debug("File successfully uploaded to GCS");
            return new UploadedImageDTO(id, blob.getName(), blob.getMediaLink(), config.getDirName(), config.getBucketId());
        } catch (IOException e) {
            log.error("An error occurred while uploading data. Exception: ", e);
            throw new GCPFileUploadException("An error occurred while storing data to GCS");
        }
    }


    private String checkFileExtension(String fileName) throws NotAnImageException {
        if(fileName != null && fileName.contains(".")){
            for(String extension : this.EXTENSION_LIST) {
                if (fileName.endsWith(extension)) {
                    log.debug("Accepted file type : {}", extension);
                    return extension;
                }
            }
        }

        log.error("Not a permitted file type");
        throw new NotAnImageException("Not a permitted file type. must be one of " + String.join(" ", this.EXTENSION_LIST));
    }


    public Bucket getBucket() throws IOException {
        InputStream inputStream = new ClassPathResource(config.getAuthFile()).getInputStream();

        StorageOptions options = StorageOptions.newBuilder().setProjectId(config.getProjectId())
                .setCredentials(GoogleCredentials.fromStream(inputStream)).build();

        Storage storage = options.getService();
        return storage.get(config.getBucketId(), Storage.BucketGetOption.fields());
    }
}