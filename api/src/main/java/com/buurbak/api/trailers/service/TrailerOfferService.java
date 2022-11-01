package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.TrailerInfoDTO;
import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.exception.TrailerTypeNotFoundException;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TrailerOfferService {
    private final TrailerOfferRepository trailerOfferRepository;
    private final CustomerService customerService;
    private final TrailerTypeService trailerTypeService;


    public TrailerOffer getTrailerOffer(UUID id) throws EntityNotFoundException {
        return trailerOfferRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<TrailerInfoDTO> getAllTrailerOffersInfo(){
        return trailerOfferRepository.findTrailersInfo();
    }

    public void addTrailerOffer(CreateTrailerOfferDTO createTrailerOfferDTO, String username) throws CustomerNotFoundException, TrailerTypeNotFoundException {
        Customer customer = customerService.findByUsername(username);
        TrailerType trailerType = trailerTypeService.findByName(createTrailerOfferDTO.trailerType());
        TrailerOffer trailerOffer = new TrailerOffer(
                trailerType,
                customer,
                createTrailerOfferDTO.length(),
                createTrailerOfferDTO.height(),
                createTrailerOfferDTO.width(),
                createTrailerOfferDTO.weight(),
                createTrailerOfferDTO.capacity(),
                createTrailerOfferDTO.licensePlateNumber(),
                createTrailerOfferDTO.pickUpTimeStart(),
                createTrailerOfferDTO.pickUpTimeEnd(),
                createTrailerOfferDTO.dropOffTimeStart(),
                createTrailerOfferDTO.dropOffTimeEnd(),
                createTrailerOfferDTO.location(),
                createTrailerOfferDTO.price(),
                createTrailerOfferDTO.available());
        trailerOfferRepository.save(trailerOffer);
    }
}
