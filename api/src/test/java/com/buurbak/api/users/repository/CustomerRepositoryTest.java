package com.buurbak.api.users.repository;

import com.buurbak.api.users.model.Customer;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        // Given
        EasyRandom easyRandom = new EasyRandom();
        Customer customer = customerRepository.save(easyRandom.nextObject(Customer.class));

        // When
        Optional<Customer> foundCustomer = customerRepository.findByEmail(customer.getEmail());

        // Then
        assertTrue(foundCustomer.isPresent());
        assertEquals(foundCustomer.get().getEmail(), customer.getEmail());
    }

    @Test
    void dontFindByEmail() {
        // Given
        String email = "luca.bergman@buurbak.nl";

        // When
        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        // Then
        assertFalse(foundCustomer.isPresent());
    }
}