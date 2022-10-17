package com.buurbak.api.images.controller;

import com.buurbak.api.images.dto.ImageDTO;
import com.buurbak.api.images.exception.BadRequestException;
import com.buurbak.api.images.exception.FileWriteException;
import com.buurbak.api.images.exception.GCPFileUploadException;
import com.buurbak.api.images.exception.NotAnImageException;
import com.buurbak.api.images.model.Image;
import com.buurbak.api.images.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "images")
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final ImageService imageService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Upload image")
    @ApiResponses({
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", description = "File should be an image", content = @Content),
            @ApiResponse(responseCode = "500", description = "Could not save profile picture", content = @Content)
    })
    public ArrayList<ImageDTO> uploadImage(@RequestParam("files") MultipartFile[] files) {
        try {
            log.info("Uploading images");
            List<Image> images = imageService.uploadImages(files);
            ArrayList<ImageDTO> uploadedImages = new ArrayList<>();

            images.forEach(image -> uploadedImages.add(new ImageDTO(
                    image.getId(),
                    image.getCreatedAt(),
                    image.getUpdatedAt(),
                    image.getOriginalFileName(),
                    image.getLocation()
            )));

            return uploadedImages;
        } catch (NotAnImageException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        } catch (GCPFileUploadException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        } catch (BadRequestException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        } catch (FileWriteException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
}
