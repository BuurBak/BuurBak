package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import com.buurbak.api.trailers.exception.TrailerOfferNotFoundException;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TrailerOfferService {
    final TrailerOfferRepository<TrailerOffer> trailerOfferRepository;

    public TrailerOffer getTrailerOffer(UUID id) throws EntityNotFoundException {
        return trailerOfferRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<TrailerOfferDTO> getAllTrailerOffersInfo(){
        return trailerOfferRepository.findTrailersInfo();
    }

    public void deleteTrailerOffer(UUID trailerId) {
//        trailerOfferRepository.existsById(trailerId);
        boolean exists = trailerOfferRepository.existsById(trailerId);
        if(!exists) {
            throw new TrailerOfferNotFoundException("Trailer with id " + trailerId + " does not exist");
        } else trailerOfferRepository.deleteById(trailerId);
        log.info("Traileroffer with id " + trailerId + " has been deleted");
        throw new TrailerOfferNotFoundException("cum");
    }
}
