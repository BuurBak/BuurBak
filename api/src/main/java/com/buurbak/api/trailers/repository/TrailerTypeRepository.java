package com.buurbak.api.trailers.repository;

import com.buurbak.api.trailers.model.TrailerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrailerTypeRepository extends JpaRepository<TrailerType, String> {
    TrailerType findByName(String name);
}
