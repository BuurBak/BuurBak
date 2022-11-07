package com.buurbak.api.trailers.controller;

import com.buurbak.api.trailers.dto.TrailerInfoDTO;
import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.exception.TrailerTypeNotFoundException;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.service.TrailerOfferService;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
    public List<TrailerInfoDTO> getAllTrailerOffer(){
        return trailerOfferService.getAllTrailerOffersInfo();
    }

    @Operation(summary = "Add new trailerOffer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addTrailerOffer(@Valid @RequestBody CreateTrailerOfferDTO createTrailerOfferDTO, Authentication authentication) {
        try {
            TrailerOffer trailerOffer = trailerOfferService.addTrailerOffer(createTrailerOfferDTO, authentication.getName());
            return trailerOffer.getId().toString();
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find customer in database", e);
        } catch (TrailerTypeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer type in database", e);
        }
    }
}



