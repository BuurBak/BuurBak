package com.buurbak.api.security.service;

import com.buurbak.api.security.repository.ConfirmationTokenRepository;
import com.buurbak.api.security.model.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken (ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> findById(UUID tokenId) {
        return confirmationTokenRepository.findById(tokenId);
    }

    @Transactional
    public void setConfirmedAtToNow(UUID tokenId) {
        confirmationTokenRepository.updateConfirmedAt(tokenId, LocalDateTime.now());
    }
}
