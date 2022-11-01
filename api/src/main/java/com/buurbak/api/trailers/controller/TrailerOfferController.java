package com.buurbak.api.trailers.controller;

import com.buurbak.api.trailers.dto.TrailerOfferDTO;
import com.buurbak.api.trailers.exception.TrailerOfferNotFoundException;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.service.TrailerOfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @Operation(summary = "Delete traileroffer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Traileroffer deleted"),
            @ApiResponse(responseCode = "400", description = "The request was not valid"),
            @ApiResponse(responseCode = "404", description = "Traileroffer not found"),
            @ApiResponse(responseCode = "500", description = "Could not delete traileroffer")
    })

    @DeleteMapping("/{id}")
    public void deleteTrailerOffer(@PathVariable UUID id) {
        try {
            trailerOfferService.deleteTrailerOffer(id);
        }
        catch (TrailerOfferNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }

    }
}



