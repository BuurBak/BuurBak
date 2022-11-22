package com.buurbak.api.security.service;

import com.buurbak.api.security.exception.AppUserNotFoundException;
import com.buurbak.api.security.model.AppUser;
import com.buurbak.api.security.repository.AppUserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {
    @Mock
    private AppUserRepository appUserRepository;
    @InjectMocks
    private AppUserService appUserService;

    @Test
    void should_return_true_email_is_taken() {
        EasyRandom easyRandom = new EasyRandom();
        AppUser appUser = easyRandom.nextObject(AppUser.class);

        when(appUserRepository.existsByEmail(appUser.getEmail())).thenReturn(true);

        boolean result = appUserService.isEmailTaken(appUser.getEmail());

        assertTrue(result);
        verify(appUserRepository, times(1)).existsByEmail(appUser.getEmail());
        verifyNoMoreInteractions(appUserRepository);
    }

    @Test
    void should_return_false_email_is_taken() {
        EasyRandom easyRandom = new EasyRandom();
        AppUser appUser = easyRandom.nextObject(AppUser.class);

        when(appUserRepository.existsByEmail(appUser.getEmail())).thenReturn(false);

        boolean result = appUserService.isEmailTaken(appUser.getEmail());

        assertFalse(result);
        verify(appUserRepository, times(1)).existsByEmail(appUser.getEmail());
        verifyNoMoreInteractions(appUserRepository);
    }

    @Test
    void should_find_by_username() {
        EasyRandom easyRandom = new EasyRandom();
        AppUser appUser = easyRandom.nextObject(AppUser.class);

        when(appUserRepository.findByEmail(appUser.getEmail())).thenReturn(Optional.of(appUser));

        AppUser result = appUserService.findByEmail(appUser.getEmail());

        assertEquals(appUser, result);
        verify(appUserRepository, times(1)).findByEmail(appUser.getEmail());
        verifyNoMoreInteractions(appUserRepository);
    }

    @Test
    void should_not_find_by_username() {
        String email = "asdf";
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(AppUserNotFoundException.class, () -> appUserService.findByEmail(email));
        verify(appUserRepository, times(1)).findByEmail(email);
        verifyNoMoreInteractions(appUserRepository);
    }
}

