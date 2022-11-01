package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TrailerOfferService {
    final TrailerOfferRepository trailerOfferRepository;
    private final CustomerService customerService;

    public TrailerOffer getTrailerOffer(UUID id) throws EntityNotFoundException {
        return trailerOfferRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<TrailerOfferDTO> getAllTrailerOffersInfo(){
        return trailerOfferRepository.findTrailersInfo();
    }

    public void addTrailerOffer(TrailerOfferDTO trailerOfferDTO, Authentication authentication) {

        Customer customer = customerService.findByUsername(authentication.getName());
//        trailerOfferDTO.setOwner(customer);
//        trailerOfferRepository.save(trailerOffer);
    }
}
