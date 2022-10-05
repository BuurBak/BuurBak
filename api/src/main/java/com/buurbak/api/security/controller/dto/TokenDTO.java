package com.buurbak.api.security.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenDTO {
    @NotBlank
    private final String access_token;
    @NotBlank
    private final String refresh_token;
}
