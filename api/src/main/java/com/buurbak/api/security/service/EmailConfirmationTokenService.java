package com.buurbak.api.security.service;

import com.buurbak.api.security.exception.EmailConfirmationTokenNotFoundException;
import com.buurbak.api.security.model.EmailConfirmationToken;
import com.buurbak.api.security.repository.EmailConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class EmailConfirmationTokenService {
    @Autowired
    private EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    public EmailConfirmationToken saveEmailConfirmationToken (EmailConfirmationToken emailConfirmationToken) {
        return emailConfirmationTokenRepository.save(emailConfirmationToken);
    }

    public EmailConfirmationToken findById(UUID id) throws EmailConfirmationTokenNotFoundException {
        return emailConfirmationTokenRepository.findById(id).orElseThrow(() -> new EmailConfirmationTokenNotFoundException(id.toString()));
    }
}
