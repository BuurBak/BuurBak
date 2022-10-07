package com.buurbak.api.trailers.repository;

import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Repository
public interface TrailerOfferRepository<T extends TrailerOffer> extends JpaRepository<T, UUID> {
//    Optional<TrailerOffer> findById(UUID id);
    @Query("SELECT new com.buurbak.api.trailers.dto.TrailerOfferDTO(t.id, t.trailerType, t.location, t.price) FROM TrailerOffer t")
    List<TrailerOfferDTO> findTrailersInfo();

    default TrailerOffer findByIdOrError(UUID id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
