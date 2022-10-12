package com.buurbak.api.users.service;

import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer findByUsername(String name) {
        return customerRepository.findByEmail(name).orElseThrow(EntityNotFoundException::new);
    }
}
