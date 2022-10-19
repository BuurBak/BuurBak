package com.buurbak.api.trailers.controller;

import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.service.TrailerOfferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public void deleteTrailerOffer(@PathVariable UUID id) {
        trailerOfferService.deleteTrailerOffer(id);
    }
}



