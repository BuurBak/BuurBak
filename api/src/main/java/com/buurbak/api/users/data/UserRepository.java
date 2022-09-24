package com.buurbak.api.users.data;

import com.buurbak.api.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, UUID> {
    Optional<User> findByEmail(String email);
}
