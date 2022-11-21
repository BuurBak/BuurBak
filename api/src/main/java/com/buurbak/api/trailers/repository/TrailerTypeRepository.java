package com.buurbak.api.trailers.repository;

import com.buurbak.api.trailers.model.TrailerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrailerTypeRepository extends JpaRepository<TrailerType, UUID> {
    Optional<TrailerType> findByName(String name);
}
