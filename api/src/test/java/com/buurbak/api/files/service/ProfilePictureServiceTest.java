package com.buurbak.api.files.service;

import com.buurbak.api.files.exception.NotAnImageException;
import com.buurbak.api.files.repository.ProfilePictureRepository;
import com.buurbak.api.security.model.User;
import com.buurbak.api.security.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProfilePictureServiceTest {

    AutoCloseable autoCloseable;

    @Autowired
    private ProfilePictureRepository profilePictureRepository;
    @Autowired
    private UserService userService;
    private ProfilePictureService profilePictureService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        profilePictureService = new ProfilePictureService(userService, profilePictureRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void notAnImage() {
        // Given
        final MultipartFile mockFile = mock(MultipartFile.class);

        // When
        when(mockFile.getContentType()).thenReturn("doc/docx");

        // Then
        assertThatThrownBy(() -> profilePictureService.setProfilePicture(mockFile, "false_username"))
                .isInstanceOf(NotAnImageException.class);
    }

    @Test
    @Disabled
    void setProfilePicture() {
        // Way too hard to write as of 13/10/2022 ~ Luca Bergman

        // Given
        final MultipartFile mockFile = mock(MultipartFile.class);
        final User user = new User("luca.bergman@buurbak.nl", "password");

        // When
        when(mockFile.getContentType()).thenReturn("image/png");
        when(userService.findByUsername(user.getEmail())).thenReturn(user);

        // Then
//        verify(profilePictureRepository.save())
        assertThatThrownBy(() -> profilePictureService.setProfilePicture(mockFile, "false_username"))
                .isInstanceOf(NotAnImageException.class);
    }
}