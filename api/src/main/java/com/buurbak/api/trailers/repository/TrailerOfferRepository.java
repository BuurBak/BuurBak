package com.buurbak.api.trailers.repository;

import com.buurbak.api.trailers.model.TrailerOffer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrailerOfferRepository extends PagingAndSortingRepository<TrailerOffer, UUID> {
//    @Query("SELECT new com.buurbak.api.trailers.dto.TrailerInfoDTO(t.id, t.trailerType.name, t.location, t.price) FROM TrailerOffer t")
//    Page<TrailerInfoDTO> findTrailersInfo(Pageable pageable);
}
