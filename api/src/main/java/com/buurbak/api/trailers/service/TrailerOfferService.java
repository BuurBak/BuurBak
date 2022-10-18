package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TrailerOfferService {
    final TrailerOfferRepository trailerOfferRepository;

    public TrailerOffer getTrailerOffer(UUID id) throws EntityNotFoundException {
        return trailerOfferRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<TrailerOfferDTO> getAllTrailerOffersInfo(){
        return trailerOfferRepository.findTrailersInfo();
    }

    public void addTrailerOffer(TrailerOffer trailerOffer) {
        trailerOfferRepository.save(trailerOffer);
    }
}
