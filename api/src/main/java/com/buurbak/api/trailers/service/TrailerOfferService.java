package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.exception.TrailerOfferNotFoundException;
import com.buurbak.api.trailers.exception.TrailerTypeNotFoundException;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Address;
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

    public Page<TrailerOffer> getAllTrailerOffers(Pageable pageable){
        return trailerOfferRepository.findAll(pageable);
    }

    public TrailerOffer addTrailerOffer(CreateTrailerOfferDTO createTrailerOfferDTO, String username) throws CustomerNotFoundException, TrailerTypeNotFoundException {
        Customer customer = customerService.findByUsername(username);
        TrailerType trailerType = trailerTypeService.findByName(createTrailerOfferDTO.getTrailerType());
        TrailerOffer trailerOffer = new TrailerOffer(
                trailerType,
                customer,
                new Address(
                        createTrailerOfferDTO.getAddress().getCity(),
                        createTrailerOfferDTO.getAddress().getStreetName(),
                        createTrailerOfferDTO.getAddress().getNumber(),
                        createTrailerOfferDTO.getAddress().getPostalCode()
                ),
                createTrailerOfferDTO.getLength(),
                createTrailerOfferDTO.getHeight(),
                createTrailerOfferDTO.getWidth(),
                createTrailerOfferDTO.getWeight(),
                createTrailerOfferDTO.getCapacity(),
                createTrailerOfferDTO.getLicensePlateNumber(),
                createTrailerOfferDTO.getPickUpTimeStart(),
                createTrailerOfferDTO.getPickUpTimeEnd(),
                createTrailerOfferDTO.getDropOffTimeStart(),
                createTrailerOfferDTO.getDropOffTimeEnd(),
                createTrailerOfferDTO.getPrice(),
                createTrailerOfferDTO.isAvailable());
        trailerOfferRepository.save(trailerOffer);
        return trailerOffer;
    }

    public TrailerOffer updateTrailerOffer(UUID trailerId, CreateTrailerOfferDTO createTrailerOfferDTO) throws TrailerTypeNotFoundException {
        TrailerOffer trailerOffer = getTrailerOffer(trailerId);
        TrailerType trailerType = trailerTypeService.findByName(createTrailerOfferDTO.getTrailerType());

        trailerOffer.setTrailerType(trailerType);
        trailerOffer.setAddress(new Address(
                createTrailerOfferDTO.getAddress().getCity(),
                createTrailerOfferDTO.getAddress().getStreetName(),
                createTrailerOfferDTO.getAddress().getNumber(),
                createTrailerOfferDTO.getAddress().getPostalCode()
        ));
        trailerOffer.setLength(createTrailerOfferDTO.getLength());
        trailerOffer.setHeight(createTrailerOfferDTO.getHeight());
        trailerOffer.setWidth(createTrailerOfferDTO.getWidth());
        trailerOffer.setWeight(createTrailerOfferDTO.getWeight());
        trailerOffer.setCapacity(createTrailerOfferDTO.getCapacity());
        trailerOffer.setLicensePlateNumber(createTrailerOfferDTO.getLicensePlateNumber());
        trailerOffer.setPickUpTimeStart(createTrailerOfferDTO.getPickUpTimeStart());
        trailerOffer.setPickUpTimeEnd(createTrailerOfferDTO.getPickUpTimeEnd());
        trailerOffer.setDropOffTimeStart(createTrailerOfferDTO.getDropOffTimeStart());
        trailerOffer.setDropOffTimeEnd(createTrailerOfferDTO.getDropOffTimeEnd());
        trailerOffer.setPrice(createTrailerOfferDTO.getPrice());
        trailerOffer.setAvailable(createTrailerOfferDTO.isAvailable());

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
