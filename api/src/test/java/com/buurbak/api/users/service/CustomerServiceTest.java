package com.buurbak.api.users.service;

import com.buurbak.api.users.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomerServiceTest {

    private AutoCloseable autoCloseable;

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findByUsername() {
        // Given
        String username = "lucabergman@buurbak.nl";

        // When
        // Then
        assertThatThrownBy(() -> customerService.findByUsername(username))
                .isInstanceOf(EntityNotFoundException.class);
    }
}