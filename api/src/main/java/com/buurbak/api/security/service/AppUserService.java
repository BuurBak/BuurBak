package com.buurbak.api.security.service;

import com.buurbak.api.security.exception.AppUserNotFoundException;
import com.buurbak.api.security.model.AppUser;
import com.buurbak.api.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AppUserService {
    private final UserRepository userRepository;

    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public AppUser findByUsername(String email) throws AppUserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new AppUserNotFoundException(email));
    }
}
