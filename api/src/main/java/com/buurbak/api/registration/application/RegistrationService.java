package com.buurbak.api.registration.application;

import com.buurbak.api.registration.domain.RegistrationRequest;
import com.buurbak.api.users.application.CustomerService;
import com.buurbak.api.users.application.UserService;
import com.buurbak.api.users.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    public final CustomerService customerService;

    public String register(RegistrationRequest request) {
        return customerService.signUpCustomer(new Customer(request.getEmail(), request.getPassword(), request.getName(), request.getDateOfBirth(), request.getIban(), request.getAddress()));
    }
}
