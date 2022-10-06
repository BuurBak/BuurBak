package com.buurbak.api.trailers.application;

import com.buurbak.api.trailers.data.TrailerOfferRepository;
import com.buurbak.api.trailers.domain.TrailerOffer;
import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrailerOfferService {
    final TrailerOfferRepository<TrailerOffer> trailerOfferRepository;

    @Autowired
    public TrailerOfferService(TrailerOfferRepository<TrailerOffer> trailerOfferRepository) {
        this.trailerOfferRepository = trailerOfferRepository;
    }

    public TrailerOffer getTrailerOffer(UUID id){
        return trailerOfferRepository.findByIdOrError(id);
    }

    public List<TrailerOfferDTO> getAllTrailerOffersInfo(){
        return trailerOfferRepository.findTrailersInfo();
    }
}