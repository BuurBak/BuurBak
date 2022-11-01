package com.buurbak.api.trailers.dto;

import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.users.model.Customer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalTime;
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
    @NotBlank(message = "Owner may not be blank")
    private final Customer owner;
    @Positive(message = "Length may only be a positive number above 0")
    private final int length;
    @Positive(message = "Height may only be a positive number above 0")
    private final int height;
    @Positive(message = "Width may only be a positive number above 0")
    private final int width;
    @Positive(message = "Weight may only be a positive number above 0")
    private final int weight;
    @Positive(message = "Capacity may only be a positive number above 0")
    private final int capacity;
    @NotBlank(message = "LicensePlate may not be blank")
    private final String licensePlateNumber;
    @Future(message = "PickUpTime may only be in the future")
    private final LocalTime pickUpTimeStart;
    @Future(message = "PickUpTime may only be in the future")
    private final LocalTime pickUpTimeEnd;
    @Future(message = "DropOff may only be in the future")
    private final LocalTime dropOffTimeStart;
    @Future(message = "DropOff may only be in the future")
    private final LocalTime dropOffTimeEnd;
    @NotBlank(message = "Location may not be blank")
    private final String location;
    @PositiveOrZero(message = "Price may only be a positive number")
    private final double price;
    private final boolean available;
}
