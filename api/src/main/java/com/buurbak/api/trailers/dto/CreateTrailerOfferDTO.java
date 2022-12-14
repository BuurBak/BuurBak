package com.buurbak.api.trailers.dto;

import com.buurbak.api.security.dto.CreateAddressDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrailerOfferDTO{
    @NotBlank(message = "TrailerType may not be blank")
    @JsonAlias("trailer_type")
    private String trailerType;

    private CreateAddressDTO address;

    @Positive(message = "Length may only be a positive number above 0")
    private int length;

    @Positive(message = "Height may only be a positive number above 0")
    private int height;

    @Positive(message = "Width may only be a positive number above 0")
    private int width;

    @Positive(message = "Weight may only be a positive number above 0")
    private int weight;

    @Positive(message = "Capacity may only be a positive number above 0")
    private int capacity;

    @NotBlank(message = "LicensePlate may not be blank")
    @JsonAlias("license_plate_number")
    private String licensePlateNumber;

    @NotNull(message = "pickUpTimeStart may not be null")
    @JsonAlias("pick_up_time_start")
    private LocalTime pickUpTimeStart;

    @NotNull(message = "PickUpTime may not be null")
    @JsonAlias("pick_up_time_end")
    private LocalTime pickUpTimeEnd;

    @NotNull(message = "DropOff may not be null")
    @JsonAlias("drop_off_time_start")
    private LocalTime dropOffTimeStart;

    @NotNull(message = "DropOff may not be null")
    @JsonAlias("drop_off_time_end")
    private LocalTime dropOffTimeEnd;

    @NotNull(message = "Latitude may not be null")
    private double latitude;

    @NotNull(message = "Longitude may not be null")
    private double longitude;

    @PositiveOrZero(message = "Price may only be a positive number")
    private double price;

    private boolean available;
}
