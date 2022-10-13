package com.buurbak.api.files.service;

import com.buurbak.api.files.repository.FileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileServiceTest {

    private AutoCloseable autoCloseable;

    @Mock
    private FileRepository fileRepository;
    private FileService fileService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        fileService = new FileService(fileRepository);
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
        assertThatThrownBy(() -> fileService.findById(uuid))
                .isInstanceOf(EntityNotFoundException.class);
    }
}