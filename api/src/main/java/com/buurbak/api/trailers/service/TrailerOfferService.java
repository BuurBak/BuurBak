package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.trailers.model.TrailerOffer;
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
