package com.buurbak.api.images.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ImageDTO {
    @NotNull
    private UUID id;

    @Past
    private Date createdAt;
    @Past
    private Date updatedAt;

    @NotBlank
    private String originalFileName;
    @NotBlank
    private String location;
}
