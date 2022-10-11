package com.buurbak.api.security.controller;

import com.buurbak.api.files.dto.ProfilePictureDTO;
import com.buurbak.api.files.exception.NotAnImageException;
import com.buurbak.api.files.model.ProfilePicture;
import com.buurbak.api.files.service.ProfilePictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping(path = "settings")
@RequiredArgsConstructor
public class SettingsController {
    private final ProfilePictureService profilePictureService;

    @Operation(summary = "Set profile picture of self")
    @ApiResponses({
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", description = "File should be an image", content = @Content),
            @ApiResponse(responseCode = "500", description = "Could not save profile picture", content = @Content)
    })
    @PostMapping("profile/picture")
    public ProfilePictureDTO setProfilePicture(Authentication authentication, @RequestParam("file") MultipartFile file) {
        try {
            ProfilePicture profilePicture = profilePictureService.setProfilePicture(file, authentication.getName());
            return new ProfilePictureDTO(
                    profilePicture.getId(),
                    profilePicture.getName(),
                    profilePicture.getSize(),
                    profilePicture.getContentType(),
                    profilePicture.getUser().getId(),
                    profilePicture.getCreatedAt(),
                    profilePicture.getUpdatedAt()
                );
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save image: " + file.getOriginalFilename(), exception);
        } catch (NotAnImageException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File: " + file.getOriginalFilename() + " must be an image", exception);
        }
    }

}
