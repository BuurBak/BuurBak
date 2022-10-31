package com.buurbak.api.trailers.controller;

import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.service.TrailerOfferService;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("traileroffer")
public class TrailerOfferController {
    private final TrailerOfferService trailerOfferService;
    private final CustomerService customerService;

    @GetMapping(path = "/{id}")
    public TrailerOffer getTrailerOffer(@PathVariable UUID id){
         return trailerOfferService.getTrailerOffer(id);
    }

    @GetMapping
    public List<TrailerOfferDTO> getAllTrailerOffer(){
        return trailerOfferService.getAllTrailerOffersInfo();
    }

    @PostMapping
    public void addTrailerOffer(@RequestBody TrailerOffer trailerOffer, Authentication authentication) {
        Customer customer = customerService.findByUsername(authentication.getName());
        trailerOffer.setUser(customer);
        trailerOfferService.addTrailerOffer(trailerOffer);
    }
}



