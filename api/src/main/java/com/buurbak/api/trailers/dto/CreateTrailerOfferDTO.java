package com.buurbak.api.trailers.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalTime;

public record CreateTrailerOfferDTO(
        @NotBlank(message = "TrailerType may not be blank") @JsonAlias("trailer_type") String trailerType,
        @Positive(message = "Length may only be a positive number above 0") int length,
        @Positive(message = "Height may only be a positive number above 0") int height,
        @Positive(message = "Width may only be a positive number above 0") int width,
        @Positive(message = "Weight may only be a positive number above 0") int weight,
        @Positive(message = "Capacity may only be a positive number above 0") int capacity,
        @NotBlank(message = "LicensePlate may not be blank") @JsonAlias("license_plate_number") String licensePlateNumber,
        @NotNull(message = "pickUpTimeStart may not be null") @JsonAlias("pick_up_time_start") LocalTime pickUpTimeStart,
        @NotNull(message = "PickUpTime may not be null") @JsonAlias("pick_up_time_end") LocalTime pickUpTimeEnd,
        @NotNull(message = "DropOff may not be null") @JsonAlias("drop_off_time_start") LocalTime dropOffTimeStart,
        @NotNull(message = "DropOff may not be null") @JsonAlias("drop_off_time_end") LocalTime dropOffTimeEnd,
        @NotBlank(message = "Location may not be blank") String location,
        @PositiveOrZero(message = "Price may only be a positive number") double price, boolean available) {
}
