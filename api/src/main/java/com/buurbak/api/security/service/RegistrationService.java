package com.buurbak.api.security.service;

import com.buurbak.api.email.service.ConfirmEmailService;
import com.buurbak.api.email.service.EmailSender;
import com.buurbak.api.security.dto.RegistrationRequestDTO;
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
    private final EmailSender emailSender;
    private final ConfirmEmailService confirmEmailService;

    public UUID register(RegistrationRequestDTO requestDTO) {
        User user = userService.signUpUser(
                new Customer(
                        requestDTO.getEmail(),
                        requestDTO.getPassword(),
                        requestDTO.getName(),
                        requestDTO.getDateOfBirth(),
                        requestDTO.getIban(),
                        requestDTO.getAddress()
                )
        );


        EmailConfirmationToken token = confirmationTokenService.createAndSaveEmailConfirmationToken(user);

        emailSender.send(
                requestDTO.getEmail(),
                confirmEmailService.buildEmail(
                        requestDTO.getName(),
                        // TODO set to env variable
                        "http://localhost:3080/api/v1/auth/confirm/" + token.getId().toString()
                        )
        );

        return user.getId();
    }

    @Transactional
    public void confirmEmail(UUID tokenId) {
        EmailConfirmationToken confirmationToken = confirmationTokenService.findById(tokenId);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAtToNow(tokenId);
        userService.verifyEmailOfUserById(confirmationToken.getUser().getId());
    }
}
