package com.buurbak.api.trailers.repository;

import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrailerOfferRepository extends JpaRepository<TrailerOffer, UUID> {
    @Query("SELECT new com.buurbak.api.trailers.dto.TrailerOfferDTO(t.id, t.trailerType, t.location, t.price) FROM TrailerOffer t")
    List<TrailerOfferDTO> findTrailersInfo();
}
