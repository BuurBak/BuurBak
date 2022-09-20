package com.buurbak.api.users.data;

import com.buurbak.api.users.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository<T extends Person> extends JpaRepository<T, UUID> {
    @Query("SELECT p FROM Person p WHERE p.email = ?1")
    Optional<Person> findPersonByEmail(String email);
}
