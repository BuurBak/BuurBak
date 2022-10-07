package com.buurbak.api.trailers.dto;

import com.buurbak.api.trailers.model.TrailerType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TrailerOfferDTO {
    private final UUID id;
    private final TrailerType trailerType;
    private final String location;
    private final double price;
}
