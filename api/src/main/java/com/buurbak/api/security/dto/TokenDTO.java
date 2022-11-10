package com.buurbak.api.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    @NotBlank @JsonProperty("access_token")
    private String accessToken;
    @NotBlank @JsonProperty("refresh_token")
    private String refreshToken;
}
