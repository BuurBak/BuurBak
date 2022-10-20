package com.buurbak.api.trailers.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class AccessoireNameDTO {
    @NotBlank(message = "name may not be blank")
    private String name;
}
