package com.buurbak.api.users.service;

import com.buurbak.api.users.dto.UpdateCustomerDTO;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(UUID id) throws EntityNotFoundException {
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Customer findByUsername(String name) throws CustomerNotFoundException {
        return customerRepository.findByEmail(name).orElseThrow(() -> new CustomerNotFoundException(name));
    }

    public Page<Customer> findAll(Specification<Customer> specification, Pageable pageable) {
        return customerRepository.findAll(specification, pageable);
    }

    public Customer updateUser(UUID id, UpdateCustomerDTO updateCustomerDTO) throws CustomerNotFoundException {
        Customer customer = getCustomer(id);

        customer.setName(updateCustomerDTO.getName());

        customerRepository.save(customer);
        return customer;
    }
}
