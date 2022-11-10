package com.buurbak.api.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record TokenDTO (
    @NotBlank @JsonProperty("access_token")
    String accessToken,
    @NotBlank @JsonProperty("refresh_token")
    String refreshToken
) {
}
