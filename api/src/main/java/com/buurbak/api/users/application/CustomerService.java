package com.buurbak.api.users.application;

import com.buurbak.api.users.data.CustomerRepository;
import com.buurbak.api.users.domain.Customer;
import com.buurbak.api.users.domain.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository<Customer> customerRepository;

    public void addNewCustomer(Customer customer) {
        Optional<Person> personByEmail = customerRepository.findPersonByEmail(customer.getEmail());

        if (personByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        customerRepository.save(customer);
    }
}
