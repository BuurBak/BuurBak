package com.buurbak.api.security.service;

import com.buurbak.api.security.model.EmailConfirmationToken;
import com.buurbak.api.security.model.AppUser;
import com.buurbak.api.security.repository.EmailConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmailConfirmationTokenService {
    private final EmailConfirmationTokenRepository emailConfirmationTokenRepository;
    public static final int EMAIL_TOKEN_DURATION = 24 * 60 * 60; // 24 hours in seconds
    public EmailConfirmationToken createAndSaveEmailConfirmationToken (AppUser appUser) {
        EmailConfirmationToken token = new EmailConfirmationToken(
                LocalDateTime.now().plusSeconds(EMAIL_TOKEN_DURATION),
                appUser
        );
        emailConfirmationTokenRepository.save(token);
        return token;
    }

    public EmailConfirmationToken findById(UUID tokenId) throws EntityNotFoundException {
        return emailConfirmationTokenRepository.findById(tokenId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void setConfirmedAtToNow(UUID tokenId) {
        emailConfirmationTokenRepository.updateConfirmedAt(tokenId, LocalDateTime.now());
    }
}
