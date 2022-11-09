package com.buurbak.api.trailers.controller;

import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.dto.TrailerInfoDTO;
import com.buurbak.api.trailers.exception.TrailerOfferNotFoundException;
import com.buurbak.api.trailers.exception.TrailerTypeNotFoundException;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
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
@RequestMapping("traileroffers")
public class TrailerOfferController {
    private final TrailerOfferService trailerOfferService;
    private final TrailerOfferRepository trailerOfferRepository;

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

    @Operation(summary = "Update trailerOffer")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content)
    })
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrailerOffer(@PathVariable UUID id, @Valid @RequestBody CreateTrailerOfferDTO createTrailerOfferDTO) {
        try {
            trailerOfferService.updateTrailerOffer(id, createTrailerOfferDTO);
        } catch (TrailerTypeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find trailer type in database", e);
        }
    }

    @Operation(summary = "Delete traileroffer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Traileroffer deleted"),
            @ApiResponse(responseCode = "404", description = "Traileroffer not found"),
            @ApiResponse(responseCode = "500", description = "Could not delete traileroffer")
    })
    @DeleteMapping("/{id}")
    public void deleteTrailerOffer(@PathVariable UUID id) {
        try {
            trailerOfferService.deleteTrailerOffer(id);
        }
        catch (TrailerOfferNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }

    }
}



