package com.buurbak.api.registration.application;

import com.buurbak.api.registration.data.ConfirmationTokenRepository;
import com.buurbak.api.registration.domain.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken (ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }
}
