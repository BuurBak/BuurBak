package com.buurbak.api.files.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProfilePictureDTO {
    @NotNull
    private final UUID id;

    @NotBlank
    private final String fileLocation;

    @NotNull
    private final UUID userId;


}
