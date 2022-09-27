package com.buurbak.api.users.repository;

import com.buurbak.api.security.repository.UserRepository;
import com.buurbak.api.users.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {
}
