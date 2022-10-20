package com.buurbak.api.trailers.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
public class AccessoireDTO {
    //TODO Check why NotBlank function isnt working on UUID
    @NotBlank(message = "trailerId may not be blank")
    private UUID trailerId;
    @NotBlank(message = "accessoireName may not be blank")
    private List<String> accessoireName;
}
