package com.buurbak.api.users.service;

import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findByUsername(String name) throws CustomerNotFoundException {
        return customerRepository.findByEmail(name).orElseThrow(() -> new CustomerNotFoundException(name));
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Page<Customer> findAll(Specification<Customer> specification, Pageable pageable) {
        return customerRepository.findAll(specification, pageable);
    }
}
