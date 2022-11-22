package com.buurbak.api.images.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicImageDTO {
    @NotNull
    private UUID id;

    @NotBlank @JsonProperty("original_file_name")
    private String originalFileName;
    @NotBlank @JsonProperty("public_url")
    private String publicUrl;
}
