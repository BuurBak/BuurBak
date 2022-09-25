package com.buurbak.api.users.application;

import com.buurbak.api.registration.application.ConfirmationTokenService;
import com.buurbak.api.registration.domain.ConfirmationToken;
import com.buurbak.api.users.data.UserRepository;
import com.buurbak.api.users.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MESSAGE = "User with email: %s not found in database";
    private final static String EMAIL_TAKEN_MESSAGE = "Email: %s already taken";
    private final static int EMAIL_CONFIRMATION_TOKEN_EXPIRATION_TIME_IN_MINUTES = 30;


    private final ConfirmationTokenService confirmationTokenService;
    private final UserRepository<User> userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public String signUpUser(User user) throws IllegalStateException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format(EMAIL_TAKEN_MESSAGE, user.getEmail()));
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(EMAIL_CONFIRMATION_TOKEN_EXPIRATION_TIME_IN_MINUTES),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);


        // TODO: SEND EMAIL

        return confirmationToken.getId().toString();
    }

}
