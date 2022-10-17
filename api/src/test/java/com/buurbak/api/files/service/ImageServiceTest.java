package com.buurbak.api.files.service;

import com.buurbak.api.files.repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageServiceTest {

    private AutoCloseable autoCloseable;

    @Mock
    private ImageRepository imageRepository;
    private ImageService imageService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        imageService = new ImageService(imageRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void cantFindById() {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        // Then
        assertThatThrownBy(() -> imageService.findById(uuid))
                .isInstanceOf(EntityNotFoundException.class);
    }
}