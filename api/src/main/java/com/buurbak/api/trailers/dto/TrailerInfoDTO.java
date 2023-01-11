package com.buurbak.api.trailers.dto;

import com.buurbak.api.images.dto.PublicImageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrailerInfoDTO {
    @NotBlank(message = "Id may not be blank")
    UUID id;

    private TrailerTypeDTO trailerType;

    @NotBlank(message = "Location may not be blank")
    String location;

    @NotBlank(message = "Price may not be blank")
    double price;

    @Valid
    Collection <PublicImageDTO> trailerOfferPictures;
}