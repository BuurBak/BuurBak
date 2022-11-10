package com.buurbak.api.users.service;

import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void should_save_customer() {
        EasyRandom easyRandom = new EasyRandom();
        Customer customer = easyRandom.nextObject(Customer.class);

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.saveCustomer(customer);

        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void should_find_by_username() {
        EasyRandom easyRandom = new EasyRandom();
        Customer customer = easyRandom.nextObject(Customer.class);

        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

        Customer result = customerService.findByUsername(customer.getEmail());

        assertEquals(customer, result);
        verify(customerRepository, times(1)).findByEmail(customer.getEmail());
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void should_not_find_by_username() {
        String email = "asdf";
        when(customerRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findByUsername(email));
        verify(customerRepository, times(1)).findByEmail(email);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    @Disabled
    void should_find_all() {
        // TODO find out how to unit test specifications and pageables
    }

    @Test
    @Disabled
    void should_find_all_that_are_not_deleted() {
        // TODO find out how to unit test specifications and pageables
    }
}