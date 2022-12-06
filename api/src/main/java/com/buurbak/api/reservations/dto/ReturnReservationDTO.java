package com.buurbak.api.reservations.dto;

import com.buurbak.api.trailers.dto.ReturnTrailerOfferDTO;
import com.buurbak.api.users.dto.PublicCustomerDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnReservationDTO {
        @NotBlank(message = "id may not be blank")
        private UUID id;

        private PublicCustomerDTO renter;

        private ReturnTrailerOfferDTO trailer;

        @NotNull(message = "startTime may not be null")
        @Future(message = "startTime may only be in the future")
        @JsonAlias("start_time")
        private LocalDateTime startTime;

        @NotNull(message = "endTime may not be null")
        @Future(message = "endTime may only be in the future")
        @JsonAlias("end_time")
        private LocalDateTime endTime;

        @NotNull(message = "confirmed may not be null")
        boolean confirmed;

        @Future(message = "confirmedAt may only be in the future")
        @JsonAlias("confirmed_at")
        private LocalDateTime confirmedAt;
}
