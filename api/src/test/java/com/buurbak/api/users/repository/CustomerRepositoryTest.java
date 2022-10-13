package com.buurbak.api.users.repository;

import com.buurbak.api.users.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        // Given
        Customer customer = new Customer(
                "luca.bergman@buurbak.nl",
                "ghjkjhg",
                "Luca Bergman",
                LocalDate.now(),
                "NLinadkljfhlak123123",
                "Heidelberglaan 7"
        );
        customerRepository.save(customer);

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