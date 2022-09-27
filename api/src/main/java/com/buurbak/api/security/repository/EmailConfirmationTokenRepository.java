package com.buurbak.api.security.repository;

import com.buurbak.api.security.model.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationToken, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE EmailConfirmationToken c SET c.confirmedAt = ?2 WHERE c.id = ?1")
    void updateConfirmedAt(UUID tokenId, LocalDateTime confirmedAt);
}