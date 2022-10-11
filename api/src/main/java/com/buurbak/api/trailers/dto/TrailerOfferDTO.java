package com.buurbak.api.trailers.dto;

import com.buurbak.api.trailers.model.TrailerType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TrailerOfferDTO {
    @NotBlank(message = "Id may not be blank")
    private final UUID id;
    @NotBlank(message = "TrailerType may not be blank")
    private final TrailerType trailerType;
    @NotBlank(message = "Location may not be blank")
    private final String location;
    @NotBlank(message = "Price may not be blank")
    private final double price;
}
