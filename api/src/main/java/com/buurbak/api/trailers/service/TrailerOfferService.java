package com.buurbak.api.trailers.service;

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

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TrailerOfferService {
    private final TrailerOfferRepository trailerOfferRepository;
    private final CustomerService customerService;
    private final TrailerTypeService trailerTypeService;


    public TrailerOffer getTrailerOffer(UUID id) throws EntityNotFoundException {
        return trailerOfferRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<TrailerOffer> getAllTrailerOffersInfo(Pageable pageable){
        return trailerOfferRepository.findAll(pageable);
    }

    public TrailerOffer addTrailerOffer(CreateTrailerOfferDTO createTrailerOfferDTO, String username) throws CustomerNotFoundException, TrailerTypeNotFoundException {
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
        return trailerOffer;
    }

    public TrailerOffer updateTrailerOffer(UUID trailerId, CreateTrailerOfferDTO createTrailerOfferDTO) throws TrailerTypeNotFoundException {
        TrailerOffer trailerOffer = getTrailerOffer(trailerId);
        TrailerType trailerType = trailerTypeService.findByName(createTrailerOfferDTO.trailerType());

        trailerOffer.setTrailerType(trailerType);
        trailerOffer.setLength(createTrailerOfferDTO.length());
        trailerOffer.setHeight(createTrailerOfferDTO.height());
        trailerOffer.setWidth(createTrailerOfferDTO.width());
        trailerOffer.setWeight(createTrailerOfferDTO.weight());
        trailerOffer.setCapacity(createTrailerOfferDTO.capacity());
        trailerOffer.setLicensePlateNumber(createTrailerOfferDTO.licensePlateNumber());
        trailerOffer.setPickUpTimeStart(createTrailerOfferDTO.pickUpTimeStart());
        trailerOffer.setPickUpTimeEnd(createTrailerOfferDTO.pickUpTimeEnd());
        trailerOffer.setDropOffTimeStart(createTrailerOfferDTO.dropOffTimeStart());
        trailerOffer.setDropOffTimeEnd(createTrailerOfferDTO.dropOffTimeEnd());
        trailerOffer.setLocation(createTrailerOfferDTO.location());
        trailerOffer.setPrice(createTrailerOfferDTO.price());
        trailerOffer.setAvailable(createTrailerOfferDTO.available());

        trailerOfferRepository.save(trailerOffer);
        return trailerOffer;
    }

    public void deleteTrailerOffer(UUID trailerId) {
        if(!trailerOfferRepository.existsById(trailerId)) {
            throw new TrailerOfferNotFoundException("Trailer with id " + trailerId + " does not exist");
        }

        trailerOfferRepository.deleteById(trailerId);
        log.info("TrailerOffer with id " + trailerId + " has been deleted");
    }
}
