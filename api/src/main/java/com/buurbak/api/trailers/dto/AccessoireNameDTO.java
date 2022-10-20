package com.buurbak.api.trailers.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessoireNameDTO {
    @NotBlank(message = "name may not be blank")
    private String name;
}
