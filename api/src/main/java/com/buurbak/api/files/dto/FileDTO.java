package com.buurbak.api.files.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FileDTO {
    @NotNull
    private UUID id;
    @NotBlank
    private String name;
    @NotNull
    private Long size;
    @NotBlank
    private String contentType;
}
