package com.buurbak.api.files.controller;

import com.buurbak.api.files.model.FileEntity;
import com.buurbak.api.files.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping(path = "files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;


    @GetMapping("{id}")
    @Operation(summary = "Get file")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
    })
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        try {
            FileEntity fileEntity = fileService.findById(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                    .contentType(MediaType.valueOf(fileEntity.getContentType()))
                    .body(fileEntity.getData());
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find file by id: " + id, exception);
        }
    }
}
