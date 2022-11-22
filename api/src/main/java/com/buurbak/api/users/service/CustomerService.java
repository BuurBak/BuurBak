package com.buurbak.api.users.service;

import com.buurbak.api.security.dto.RegisterNewCustomerDTO;
import com.buurbak.api.users.dto.UpdateCustomerDTO;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Address;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

        Address address = customer.getAddress();

        address.setCity(updateCustomerDTO.getAddress().getCity());
        address.setStreetName(updateCustomerDTO.getAddress().getStreetName());
        address.setNumber(updateCustomerDTO.getAddress().getNumber());
        address.setPostalCode(updateCustomerDTO.getAddress().getPostalCode());

        customer.setEmail(updateCustomerDTO.getEmail());
        customer.setName(updateCustomerDTO.getName());

        customer.setPassword(bCryptPasswordEncoder.encode(updateCustomerDTO.getPassword()));

        customer.setNumber(updateCustomerDTO.getNumber());

        customer.setAddress(address);

        customer.setDateOfBirth(updateCustomerDTO.getDateOfBirth());
        customer.setIban(updateCustomerDTO.getIban());


        customerRepository.save(customer);

        log.info("Customer with id " + id + " has been updated");

        return customer;
    }
}
