package com.buurbak.api.users.repository;

import com.buurbak.api.users.model.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, UUID>, JpaSpecificationExecutor<Customer> {
    Optional<Customer> findByEmail(String email);
}