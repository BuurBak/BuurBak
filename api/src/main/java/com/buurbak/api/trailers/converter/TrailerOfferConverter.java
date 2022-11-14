package com.buurbak.api.trailers.converter;

import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import org.modelmapper.ModelMapper;

public class TrailerOfferConverter {
    public TrailerOffer convertTrailerOfferDTOtoTrailerOffer (CreateTrailerOfferDTO trailerOfferDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(trailerOfferDTO, TrailerOffer.class);
    }
}
