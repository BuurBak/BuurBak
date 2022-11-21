package com.buurbak.api.security.repository;

import com.buurbak.api.security.model.AppUser;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        // Given
        EasyRandom easyRandom = new EasyRandom();
        AppUser appUser = appUserRepository.save(easyRandom.nextObject(AppUser.class));

        // When
        Optional<AppUser> foundAppUser = appUserRepository.findByEmail(appUser.getEmail());

        // Then
        assertTrue(foundAppUser.isPresent());
        assertEquals(foundAppUser.get().getEmail(), appUser.getEmail());
    }
}