package com.buurbak.api.users.data;

import com.buurbak.api.users.domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {
}
