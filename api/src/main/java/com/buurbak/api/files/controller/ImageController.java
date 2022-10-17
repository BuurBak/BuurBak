package com.buurbak.api.files.controller;

import com.buurbak.api.files.exception.NotAnImageException;
import com.buurbak.api.files.model.ImageEntity;
import com.buurbak.api.files.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    @Operation(summary = "Upload image")
    @ApiResponses({
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", description = "File should be an image", content = @Content),
            @ApiResponse(responseCode = "500", description = "Could not save profile picture", content = @Content)
    })
    public String uploadImage( @RequestParam("file") MultipartFile file) {
        try {
            return "uploaded image";
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save image: " + file.getOriginalFilename(), exception);
        } catch (NotAnImageException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File: " + file.getOriginalFilename() + " must be an image", exception);
        }
    }


//    @GetMapping("{id}")
//    @Operation(summary = "Get file")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.ALL_VALUE)),
//            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
//    })
//    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
//        try {
//            ImageEntity fileEntity = imageService.findById(id);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
//                    .contentType(MediaType.valueOf(fileEntity.getContentType()))
//                    .body(fileEntity.getData());
//        } catch (EntityNotFoundException exception) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find file by id: " + id, exception);
//        }
//    }
}
