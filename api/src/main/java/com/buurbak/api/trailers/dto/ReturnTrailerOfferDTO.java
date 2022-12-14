package com.buurbak.api.trailers.dto;

import com.buurbak.api.security.dto.ReturnAddressCityDTO;
import com.buurbak.api.users.dto.PublicCustomerDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnTrailerOfferDTO {
    @NotBlank(message = "id may not be blank")
    private UUID id;

    @Valid
    private TrailerTypeDTO trailerType;

    @Valid
    private PublicCustomerDTO owner;

    @Valid
    private ReturnAddressCityDTO address;

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
    private double fakeLatitude;

    @NotNull(message = "Longitude may not be null")
    private double fakeLongitude;

    @NotBlank(message = "Description may not be blank")
    @Size(max = 1000)
    private String description;

    @PositiveOrZero(message = "Price may only be a positive number")
    private double price;

    private boolean available;

    @Past
    private LocalDateTime createdAt;

    @Past
    private LocalDateTime updatedAt;
}
