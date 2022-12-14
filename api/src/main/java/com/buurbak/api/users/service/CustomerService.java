package com.buurbak.api.users.service;

import com.buurbak.api.images.model.Image;
import com.buurbak.api.images.service.ImageService;
import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.reservations.repository.ReservationRepository;
import com.buurbak.api.users.converter.CustomerConverter;
import com.buurbak.api.users.dto.UpdateCustomerDTO;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    private ImageService imageService;

    private final ReservationRepository reservationRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(UUID id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findByUsername(String name) throws CustomerNotFoundException {
        return customerRepository.findByEmail(name).orElseThrow(() -> new CustomerNotFoundException(name));
    }

    public Page<Customer> findAll(Specification<Customer> specification, Pageable pageable) {
        return customerRepository.findAll(specification, pageable);
    }

    public Page<Reservation> getAllReservations(UUID customerId, Pageable pageable){
//        return reservationRepository.findAll(pageable);
        return reservationRepository.findAllByRenterId(customerId, pageable);
    }

    public Customer updateUser(UUID id, UpdateCustomerDTO updateCustomerDTO) throws CustomerNotFoundException {
        // if guard
        Customer customer = getCustomer(id);
        Image image = imageService.findById(updateCustomerDTO.getProfilePicture().getId());
        Customer newCustomer = new CustomerConverter().convertUploadCustomerDTOToCustomer(updateCustomerDTO);

        newCustomer.setId(id);
        newCustomer.setProfilePicture(image);
        newCustomer.setCreatedAt(customer.getCreatedAt());
        newCustomer.setPassword(bCryptPasswordEncoder.encode(newCustomer.getPassword()));

        customerRepository.save(newCustomer);

        log.info("Customer with id " + id + " has been updated");

        return newCustomer;
    }

    public void deleteUser(UUID id) throws CustomerNotFoundException {

        Customer customer = getCustomer(id);
        customer.setDeleted(true);

        customerRepository.save(customer);
    }
}
