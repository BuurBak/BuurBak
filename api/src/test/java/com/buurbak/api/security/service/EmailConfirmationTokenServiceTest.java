package com.buurbak.api.security.service;

import com.buurbak.api.security.exception.EmailConfirmationTokenNotFoundException;
import com.buurbak.api.security.model.EmailConfirmationToken;
import com.buurbak.api.security.repository.EmailConfirmationTokenRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailConfirmationTokenServiceTest {

    @Mock
    private EmailConfirmationTokenRepository emailConfirmationTokenRepository;
    @InjectMocks
    private EmailConfirmationTokenService emailConfirmationTokenService;

    @Test
    void should_save_email_confirmation_token() {
        EasyRandom easyRandom = new EasyRandom();
        EmailConfirmationToken emailConfirmationToken = easyRandom.nextObject(EmailConfirmationToken.class);

        when(emailConfirmationTokenRepository.save(any(EmailConfirmationToken.class))).thenReturn(emailConfirmationToken);

        EmailConfirmationToken result = emailConfirmationTokenService.saveEmailConfirmationToken(emailConfirmationToken);

        assertEquals(emailConfirmationToken, result);
        verify(emailConfirmationTokenRepository, times(1)).save(any(EmailConfirmationToken.class));
        verifyNoMoreInteractions(emailConfirmationTokenRepository);
    }

    @Test
    void should_find_by_id() {
        EasyRandom easyRandom = new EasyRandom();
        EmailConfirmationToken emailConfirmationToken = easyRandom.nextObject(EmailConfirmationToken.class);

        when(emailConfirmationTokenRepository.findById(emailConfirmationToken.getId())).thenReturn(Optional.of(emailConfirmationToken));

        EmailConfirmationToken result = emailConfirmationTokenService.findById(emailConfirmationToken.getId());

        assertEquals(emailConfirmationToken, result);
        verify(emailConfirmationTokenRepository, times(1)).findById(emailConfirmationToken.getId());
        verifyNoMoreInteractions(emailConfirmationTokenRepository);
    }

    @Test
    void should_not_find_by_id() {
        UUID uuid = UUID.randomUUID();
        when(emailConfirmationTokenRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(EmailConfirmationTokenNotFoundException.class, () -> emailConfirmationTokenService.findById(uuid));
        verify(emailConfirmationTokenRepository, times(1)).findById(uuid);
        verifyNoMoreInteractions(emailConfirmationTokenRepository);
    }
}