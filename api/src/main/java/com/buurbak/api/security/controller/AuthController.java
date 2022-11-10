package com.buurbak.api.security.controller;

import com.buurbak.api.security.dto.RegisterNewCustomerDTO;
import com.buurbak.api.security.dto.TokenDTO;
import com.buurbak.api.security.exception.EmailConfirmationTokenAlreadyConfirmedException;
import com.buurbak.api.security.exception.EmailConfirmationTokenNotFoundException;
import com.buurbak.api.security.exception.EmailTakenException;
import com.buurbak.api.security.service.AuthService;
import com.buurbak.api.security.service.RegistrationService;
import com.buurbak.api.users.model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RegistrationService registrationService;


    @Operation(summary = "Register new Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @PostMapping("register")
    public String register(@Valid @RequestBody RegisterNewCustomerDTO registerNewCustomerDTO) {
        try {
            Customer customer = registrationService.registerNewCustomer(registerNewCustomerDTO);
            return customer.getId().toString();
        } catch (EmailTakenException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Operation(summary = "Refresh access and refresh tokens")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("refresh")
    public TokenDTO refreshAccessAndRefreshTokens(HttpServletRequest request) {
        try {
            return this.authService.refreshAccessAndRefreshTokens(request);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Confirm email from customer")
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
        } catch (EmailConfirmationTokenNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EmailConfirmationTokenAlreadyConfirmedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Id: %s is not a valid UUID", emailTokenUUID));
        }
    }
}
