package com.buurbak.api.security.service;

import com.buurbak.api.security.model.User;
import com.buurbak.api.security.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User signUpUser(User user) throws IllegalStateException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format(EMAIL_TAKEN_MESSAGE, user.getEmail()));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return user;
    }

    public void enableUser(UUID userId) {
        userRepository.enableUserById(userId);
    }

    public User findByUsername(String email) throws EntityNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
}
