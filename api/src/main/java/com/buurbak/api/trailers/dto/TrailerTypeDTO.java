package com.buurbak.api.trailers.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrailerTypeDTO {
    @NotBlank(message = "Name may not be blank")
    @JsonAlias("trailer_type")
    private String name;
}
