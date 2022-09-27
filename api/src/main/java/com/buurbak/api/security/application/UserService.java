package com.buurbak.api.security.application;

import com.buurbak.api.security.data.UserRepository;
import com.buurbak.api.security.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final static String EMAIL_TAKEN_MESSAGE = "Email: %s already taken";

    private final UserRepository<User> userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signUpUser(User user) throws IllegalStateException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format(EMAIL_TAKEN_MESSAGE, user.getEmail()));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        // TODO: create email confirmation token
        // TODO: save email confirmation token
        // TODO: send email confirmation token via email

//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(EMAIL_CONFIRMATION_TOKEN_EXPIRATION_TIME_IN_MINUTES),
//                user
//        );
//        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return user.getId().toString();
    }

    public void enableUser(UUID userId) {
        userRepository.enableUserById(userId);
    }

    public User findByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
}
