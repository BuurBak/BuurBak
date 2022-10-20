package com.buurbak.api.security.controller;

import com.buurbak.api.security.dto.RegistrationRequestDTO;
import com.buurbak.api.security.dto.TokenDTO;
import com.buurbak.api.security.service.AuthService;
import com.buurbak.api.security.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private RegistrationService registrationService;

    @Operation(summary = "Register new Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @PostMapping("register")
    public UUID register(@Valid @RequestBody RegistrationRequestDTO requestDTO) {
        return registrationService.register(requestDTO);
    }

    @Operation(summary = "Refresh access token")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping(path = "refresh")
    public TokenDTO refreshToken(HttpServletRequest request) {
        try {
            return this.authService.refreshToken(request);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Confirm email and activate user")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Token not found", content = @Content)
    })
    @GetMapping("confirm/{emailTokenUUID}")
    public String confirmEmail(@PathVariable String emailTokenUUID) {
        try {
            registrationService.confirmEmail(UUID.fromString(emailTokenUUID));
            // TODO send to success page on frontend
            return "Confirmed email";
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
