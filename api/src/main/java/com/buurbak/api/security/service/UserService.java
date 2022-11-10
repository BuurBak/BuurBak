package com.buurbak.api.security.service;

import com.buurbak.api.security.model.AppUser;
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

    public AppUser signUpUser(AppUser appUser) throws IllegalStateException {
        if (userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format(EMAIL_TAKEN_MESSAGE, appUser.getEmail()));
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        userRepository.save(appUser);

        return appUser;
    }

    public void enableUser(UUID userId) {
        userRepository.enableUserById(userId);
    }

    public AppUser findByUsername(String email) throws EntityNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
}
