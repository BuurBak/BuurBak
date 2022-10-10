package com.buurbak.api.files.service;

import com.buurbak.api.files.model.FileEntity;
import com.buurbak.api.files.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void save(MultipartFile file) throws IOException {
        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            fileEntity.setContentType(file.getContentType());
            fileEntity.setData(file.getBytes());
            fileEntity.setSize(file.getSize());

            fileRepository.save(fileEntity);
        } catch (IOException exception) {
            throw new IOException("Could not save file: " + file.getOriginalFilename(), exception);
        }
    }

    public FileEntity findById(UUID id) throws EntityNotFoundException {
        return fileRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

//    public List<FileEntity> getAllFiles() {
//        return fileRepository.findAll();
//    }
}
