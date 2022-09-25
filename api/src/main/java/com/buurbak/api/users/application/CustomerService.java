package com.buurbak.api.users.application;

import com.buurbak.api.registration.domain.ConfirmationToken;
import com.buurbak.api.users.data.CustomerRepository;
import com.buurbak.api.users.domain.Customer;
import com.buurbak.api.users.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

}
