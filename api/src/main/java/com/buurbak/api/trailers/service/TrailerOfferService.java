package com.buurbak.api.trailers.service;

import com.buurbak.api.email.service.ContactExchangeEmailService;
import com.buurbak.api.trailers.converter.TrailerOfferConverter;
import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.exception.TrailerOfferNotFoundException;
import com.buurbak.api.trailers.exception.TrailerTypeNotFoundException;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TrailerOfferService {
    private final TrailerOfferRepository trailerOfferRepository;
    private final CustomerService customerService;
    private final TrailerTypeService trailerTypeService;
    private final ContactExchangeEmailService contactExchangeEmailService;

    public TrailerOffer getTrailerOffer(UUID id) throws TrailerOfferNotFoundException {
        return trailerOfferRepository.findById(id).orElseThrow(TrailerOfferNotFoundException::new);
    }

    public Page<TrailerOffer> getAllTrailerOffers(Pageable pageable){
        return trailerOfferRepository.findAll(pageable);
    }

    public TrailerOffer addTrailerOffer(CreateTrailerOfferDTO createTrailerOfferDTO, String username) throws CustomerNotFoundException, TrailerTypeNotFoundException {
        Customer customer = customerService.findByUsername(username);
        TrailerType trailerType = trailerTypeService.findByName(createTrailerOfferDTO.getTrailerType());

        TrailerOffer trailerOffer = new TrailerOfferConverter().convertTrailerOfferDTOtoTrailerOffer(createTrailerOfferDTO);
        trailerOffer.setTrailerType(trailerType);
        trailerOffer.setOwner(customer);

        trailerOfferRepository.save(trailerOffer);
        return trailerOffer;
    }

    public void updateTrailerOffer(UUID trailerId, CreateTrailerOfferDTO createTrailerOfferDTO) throws TrailerOfferNotFoundException, TrailerTypeNotFoundException {
        TrailerOffer trailerOffer = getTrailerOffer(trailerId);
        TrailerType trailerType = trailerTypeService.findByName(createTrailerOfferDTO.getTrailerType());

        TrailerOffer newTrailerOffer = new TrailerOfferConverter().convertTrailerOfferDTOtoTrailerOffer(createTrailerOfferDTO);
        newTrailerOffer.setId(trailerId);
        newTrailerOffer.setTrailerType(trailerType);
        newTrailerOffer.setOwner(trailerOffer.getOwner());
        newTrailerOffer.setCreatedAt(trailerOffer.getCreatedAt());

        trailerOfferRepository.save(newTrailerOffer);
    }

    public void deleteTrailerOffer(UUID trailerId) {
        if(!trailerOfferRepository.existsById(trailerId)) {
            throw new TrailerOfferNotFoundException("Trailer with id " + trailerId + " does not exist");
        }

        trailerOfferRepository.deleteById(trailerId);
        log.info("TrailerOffer with id " + trailerId + " has been deleted");
    }

    public void addTrailerOfferPictures(UUID trailerId) {

    }
}
