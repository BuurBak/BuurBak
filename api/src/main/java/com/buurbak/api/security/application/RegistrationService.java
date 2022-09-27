package com.buurbak.api.security.application;

import com.buurbak.api.security.domain.ConfirmationToken;
import com.buurbak.api.security.presentation.dto.RegistrationRequestDTO;
import com.buurbak.api.users.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {

    public final UserService userService;
    public final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequestDTO requestDTO) {
        return userService.signUpUser(new Customer(requestDTO.getEmail(), requestDTO.getPassword(), requestDTO.getName(), requestDTO.getDateOfBirth(), requestDTO.getIban(), requestDTO.getAddress()));
    }

    @Transactional
    public String confirmEmail(UUID tokenId) {
        ConfirmationToken confirmationToken = confirmationTokenService.findById(tokenId)
                .orElseThrow(() -> new IllegalStateException("Could not find token by id: " + tokenId));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAtToNow(tokenId);
        userService.enableUser(confirmationToken.getUser().getId());

        return "confirmed";
    }
}
