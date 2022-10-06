package com.buurbak.api.trailers.presentation;

import com.buurbak.api.trailers.application.TrailerOfferService;
import com.buurbak.api.trailers.domain.TrailerOffer;
import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("traileroffer")
public class TrailerOfferController {
    private final TrailerOfferService trailerOfferService;

    @GetMapping(path = "/{id}")
    public TrailerOffer getTrailerOffer(@PathVariable UUID id){
         return trailerOfferService.getTrailerOffer(id);
    }

    @GetMapping
    public List<TrailerOfferDTO> getAllTrailerOffer(){
        return trailerOfferService.getAllTrailerOffersInfo();
    }
}



