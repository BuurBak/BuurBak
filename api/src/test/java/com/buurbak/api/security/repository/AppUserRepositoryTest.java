package com.buurbak.api.security.repository;

import com.buurbak.api.security.model.AppUser;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }

    @Test
    void shouldExistByEmail() {
        // Given
        EasyRandom easyRandom = new EasyRandom();
        AppUser appUser = appUserRepository.save(easyRandom.nextObject(AppUser.class));

        // When
        boolean result = appUserRepository.existsByEmail(appUser.getEmail());

        // Then
        assertTrue(result);
    }

    @Test
    void shouldNotExistByEmail() {
        // Given
        EasyRandom easyRandom = new EasyRandom();

        // When
        boolean result = appUserRepository.existsByEmail(easyRandom.nextObject(String.class));

        // Then
        assertFalse(result);
    }

    @Test
    void shouldFindByEmail() {
        // Given
        EasyRandom easyRandom = new EasyRandom();
        AppUser appUser = appUserRepository.save(easyRandom.nextObject(AppUser.class));

        // When
        Optional<AppUser> foundAppUser = appUserRepository.findByEmail(appUser.getEmail());

        // Then
        assertTrue(foundAppUser.isPresent());
        assertEquals(foundAppUser.get(), appUser);
    }

    @Test
    void shouldNotFindByEmail() {
        // Given
        EasyRandom easyRandom = new EasyRandom();

        // When
        Optional<AppUser> foundAppUser = appUserRepository.findByEmail(easyRandom.nextObject(String.class));

        // Then
        assertTrue(foundAppUser.isEmpty());
    }
}