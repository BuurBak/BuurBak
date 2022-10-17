package com.buurbak.api.images.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UploadedImageDTO {
    @NotNull
    private UUID id;

    @NotBlank
    private String originalFileName;

    @NotBlank
    private String location;
}
