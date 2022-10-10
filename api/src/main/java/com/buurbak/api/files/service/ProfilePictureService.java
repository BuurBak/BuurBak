package com.buurbak.api.files.service;

import com.buurbak.api.files.model.FileEntity;
import com.buurbak.api.files.model.ProfilePicture;
import com.buurbak.api.files.repository.FileRepository;
import com.buurbak.api.security.model.User;
import com.buurbak.api.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfilePictureService {
    private final UserService userService;
    private final FileRepository fileRepository;

    public ProfilePicture save (MultipartFile file, String username) throws IOException {
        try {
            User user = userService.findByUsername(username);

            // Make sure only one profile picture exists
            ProfilePicture existingProfileEntity = user.getProfilePicture();
            if (existingProfileEntity != null) {
                fileRepository.delete(existingProfileEntity);
            }

            ProfilePicture profilePicture = new ProfilePicture();
            profilePicture.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            profilePicture.setContentType(file.getContentType());
            profilePicture.setData(file.getBytes());
            profilePicture.setSize(file.getSize());
            profilePicture.setUser(user);

            user.setProfilePicture(profilePicture); // FetchType.LAZY on User entity so has to be done manually

            fileRepository.save(profilePicture);

            return profilePicture;
        } catch (IOException exception) {
            throw new IOException("Could not save profile picture to database: " + file.getOriginalFilename(), exception);
        }
    }
}
