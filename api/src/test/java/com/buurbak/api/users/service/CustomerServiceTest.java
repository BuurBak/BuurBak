package com.buurbak.api.users.service;

import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.reservations.repository.ReservationRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ReservationRepository reservationRepository;
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
    void shouldReturnEmtpyReservationPage(){
        // Given
        List<Reservation> reservationList = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 20);
        UUID id = UUID.randomUUID();

        // When
        when(reservationRepository.findAllByRenterId(id, pageable)).thenReturn(new PageImpl<>(reservationList));

        Page<Reservation> result = customerService.getAllReservations(id, pageable);

        // Then
        assertTrue(result.getContent().isEmpty());
        verify(reservationRepository, times(1)).findAllByRenterId(id, pageable);
        verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    void shouldReturnCustomerPage(){
        // Given
        EasyRandom easyRandom = new EasyRandom();
        Customer customer = easyRandom.nextObject(Customer.class);
        List<Reservation> reservationList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            Reservation reservation = easyRandom.nextObject(Reservation.class);
            reservation.setRenter(customer);
            reservationList.add(reservation);
        }
        Pageable pageable = PageRequest.of(0, 20);

        // When
        when(reservationRepository.findAllByRenterId(customer.getId(), pageable)).thenReturn(new PageImpl<>(reservationList));


        Page<Reservation> result = customerService.getAllReservations(customer.getId(), pageable);

        // Then
        assertEquals(reservationList, result.getContent());
        verify(reservationRepository, times(1)).findAllByRenterId(customer.getId(), pageable);
        verifyNoMoreInteractions(reservationRepository);
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