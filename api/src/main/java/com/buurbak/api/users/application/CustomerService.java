package com.buurbak.api.users.application;

import com.buurbak.api.users.data.CustomerRepository;
import com.buurbak.api.users.domain.Customer;
import com.buurbak.api.users.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final static String EMAIL_TAKEN_MESSAGE = "Email: %s already taken";

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signUpCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalStateException(String.format(EMAIL_TAKEN_MESSAGE, customer.getEmail()));
        }

        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());

        customer.setPassword(encodedPassword);

        customerRepository.save(customer);

        // TODO: send confirmation token

        return "";
    }
}
