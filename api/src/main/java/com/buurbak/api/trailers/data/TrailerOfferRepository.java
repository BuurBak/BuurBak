package com.buurbak.api.trailers.data;

import com.buurbak.api.trailers.domain.TrailerOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrailerOfferRepository<T extends TrailerOffer> extends JpaRepository<T, UUID> {
        @Query("SELECT t.id, t.trailerType, t.location, t.price FROM TrailerOffer t")
        List<Object> getAllTrailers();

    @Query("SELECT t FROM TrailerOffer t WHERE t.id = ?1")
    TrailerOffer getOneTrailer(UUID id);
}
