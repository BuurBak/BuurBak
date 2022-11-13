package com.buurbak.api.images.service;

import com.buurbak.api.images.repository.ImageRepository;
import com.buurbak.api.images.util.DataBucketUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageServiceTest {

    private AutoCloseable autoCloseable;

    @Mock
    private ImageRepository imageRepository;
    @Mock
    private DataBucketUtil dataBucketUtil;
    private ImageService imageService;

//    @BeforeEach
//    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
//        imageService = new ImageService(imageRepository, dataBucketUtil);
//    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void cantFindById() {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        // Then
        assertThatThrownBy(() -> imageService.findById(uuid))
                .isInstanceOf(EntityNotFoundException.class);
    }
}