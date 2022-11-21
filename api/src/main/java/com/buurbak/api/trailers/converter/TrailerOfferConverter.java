package com.buurbak.api.trailers.converter;

import com.buurbak.api.trailers.dto.TrailerInfoDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrailerOfferConverter {
    public TrailerInfoDTO convertTrailerOfferToTrailerInfoDTO (TrailerOffer trailerOffer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(trailerOffer, TrailerInfoDTO.class);
    }

    public Page<TrailerInfoDTO> convertTrailerOfferPageToTrailerOfferDTOPage(Page<TrailerOffer> trailerOfferPage){
        List<TrailerInfoDTO> TrailerOfferDTOList = trailerOfferPage
                .stream()
                .map(this::convertTrailerOfferToTrailerInfoDTO)
                .toList();
        return new PageImpl<>(TrailerOfferDTOList, trailerOfferPage.getPageable(), trailerOfferPage.getTotalElements());
    }
}