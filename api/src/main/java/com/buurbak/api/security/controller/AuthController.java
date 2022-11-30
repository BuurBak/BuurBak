package com.buurbak.api.security.controller;

import com.buurbak.api.security.dto.LoginUserDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private RegistrationService registrationService;

    @Operation(summary = "Login")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Bad username or password", content = @Content),
    })
    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO generateTokens(@RequestBody @Valid LoginUserDTO loginUserDTO) throws AuthenticationException {
        try {
            return authService.generateTokens(loginUserDTO);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @Operation(summary = "Register new Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@Valid @RequestBody RegisterNewCustomerDTO registerNewCustomerDTO, HttpServletRequest request) {
        try {
            Customer customer = registrationService.registerNewCustomer(registerNewCustomerDTO, request);
            return customer.getId().toString();
        } catch (EmailTakenException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Operation(summary = "Refresh tokens")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("refresh")
    public TokenDTO refreshAccessAndRefreshTokens(Authentication authentication) {
        return this.authService.refreshTokens(authentication);
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
