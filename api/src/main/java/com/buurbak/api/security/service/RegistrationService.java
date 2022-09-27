package com.buurbak.api.security.service;

import com.buurbak.api.security.controller.dto.RegistrationRequestDTO;
import com.buurbak.api.security.model.EmailConfirmationToken;
import com.buurbak.api.security.model.User;
import com.buurbak.api.users.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {

    public final UserService userService;
    public final EmailConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequestDTO requestDTO) {
        User user = userService.signUpUser(new Customer(requestDTO.getEmail(), requestDTO.getPassword(), requestDTO.getName(), requestDTO.getDateOfBirth(), requestDTO.getIban(), requestDTO.getAddress()));

        confirmationTokenService.createAndSaveEmailConfirmationToken(user);

        // TODO: send email confirmation token via email

        return user.getId().toString();
    }

    @Transactional
    public String confirmEmail(UUID tokenId) {
        EmailConfirmationToken confirmationToken = confirmationTokenService.findById(tokenId);

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
