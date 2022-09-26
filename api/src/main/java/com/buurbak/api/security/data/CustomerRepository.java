package com.buurbak.api.security.data;

import com.buurbak.api.security.domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {
}
