package com.buurbak.api.security.application;

import com.buurbak.api.security.data.ConfirmationTokenRepository;
import com.buurbak.api.security.domain.ConfirmationToken;
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
