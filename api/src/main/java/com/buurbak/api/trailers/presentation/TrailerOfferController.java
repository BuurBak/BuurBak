package com.buurbak.api.trailers.presentation;

import com.buurbak.api.trailers.application.TrailerService;
import com.buurbak.api.trailers.domain.Trailer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("trailer")
public class TrailerOfferController {
    private final TrailerService trailerService;

    @GetMapping(path = "/{id}")
    public Trailer getTrailer(@PathVariable UUID id){
         return trailerService.getTrailer(id);
    }

    @GetMapping
    public List<Trailer> getAllTrailers(){
        return trailerService.getAllTrailers();
    }
}



