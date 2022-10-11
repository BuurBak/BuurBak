package com.buurbak.api.files.service;

import com.buurbak.api.files.model.ProfilePicture;
import com.buurbak.api.files.repository.ProfilePictureRepository;
import com.buurbak.api.security.model.User;
import com.buurbak.api.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfilePictureService {
    private final UserService userService;
    private final ProfilePictureRepository profilePictureRepository;

    @Transactional
    public ProfilePicture setProfilePicture(MultipartFile file, String username) throws IOException {
        try {
            User user = userService.findByUsername(username);
            ProfilePicture profilePicture = user.getProfilePicture();

            if (profilePicture == null) {
                // profile picture does not exist yet, create it.
                profilePicture = new ProfilePicture();
                profilePicture.setUser(user);
            }

            profilePicture.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            profilePicture.setContentType(file.getContentType());
            profilePicture.setData(file.getBytes());
            profilePicture.setSize(file.getSize());

            profilePictureRepository.save(profilePicture);

            return profilePicture;
        } catch (IOException exception) {
            throw new IOException("Could not save profile picture to database: " + file.getOriginalFilename(), exception);
        }
    }
}
