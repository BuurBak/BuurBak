package com.buurbak.api.trailers.dto;

import com.buurbak.api.trailers.model.TrailerType;
import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record TrailerInfoDTO(@NotBlank(message = "Id may not be blank") UUID id,
                             @NotBlank(message = "TrailerType may not be blank") @JsonAlias("trailer_type") TrailerType trailerType,
                             @NotBlank(message = "Location may not be blank") String location,
                             @NotBlank(message = "Price may not be blank") double price) {
}