package com.buurbak.api.images.util;

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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Component
@Slf4j
public class DataBucketUtil {
    private final String[] EXTENSION_LIST = {".png", ".jpeg"};

    @Value("${gcp.config.file}")
    private String gcpConfigFile;

    @Value("${gcp.project.id}")
    private String gcpProjectId;

    @Value("${gcp.bucket.id}")
    private String gcpBucketId;

    @Value("${gcp.dir.name}")
    private String gcpDirectoryName;

    public UploadedImageDTO uploadFile(MultipartFile multipartFile, String fileName, String contentType) throws GCPFileUploadException, NotAnImageException, BadRequestException, FileWriteException {
        try {
            log.debug("Start file uploading process on GCS");
            byte[] fileData = FileUtils.readFileToByteArray(convertFile(multipartFile));

            InputStream inputStream = new ClassPathResource(gcpConfigFile).getInputStream();

            StorageOptions options = StorageOptions.newBuilder().setProjectId(gcpProjectId)
                    .setCredentials(GoogleCredentials.fromStream(inputStream)).build();

            Storage storage = options.getService();
            Bucket bucket = storage.get(gcpBucketId,Storage.BucketGetOption.fields());

            UUID id = UUID.randomUUID();

            String fileExtension = checkFileExtension(fileName);
            Blob blob = bucket.create(gcpDirectoryName + "/" + id + fileExtension, fileData, contentType);

            log.debug("File successfully uploaded to GCS");
            return new UploadedImageDTO(id, blob.getName(), blob.getMediaLink());
        } catch (IOException e) {
            log.error("An error occurred while uploading data. Exception: ", e);
            throw new GCPFileUploadException("An error occurred while storing data to GCS");
        }
    }


    private File convertFile(MultipartFile file) throws BadRequestException, FileWriteException {
        if (file.getOriginalFilename() == null){
            throw new BadRequestException("Original file name is null");
        }

        try {
            File convertedFile = new File(file.getOriginalFilename());
            FileOutputStream outputStream = new FileOutputStream(convertedFile);
            outputStream.write(file.getBytes());
            outputStream.close();
            log.debug("Converting multipart file : {}", convertedFile);
            return convertedFile;
        } catch (Exception e) {
            throw new FileWriteException("An error has occurred while converting the file");
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
}