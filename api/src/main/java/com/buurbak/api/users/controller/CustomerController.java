package com.buurbak.api.users.controller;

import com.buurbak.api.files.model.ProfilePicture;
import com.buurbak.api.users.dto.CustomerDTO;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("self")
    @Operation(summary = "Get self")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
    })
    public CustomerDTO getCustomerSelf(Authentication authentication) {
        try {
            Customer customer = customerService.findByUsername(authentication.getName());
            ProfilePicture profilePicture = customer.getProfilePicture();

            UUID profilePictureId = null;
            if (profilePicture != null) {
                profilePictureId = profilePicture.getId();
            }

            return new CustomerDTO(
                customer.getId(),
                customer.getEmail(),
                customer.getName(),
                customer.getDateOfBirth(),
                customer.getIban(),
                customer.getAddress(),
                profilePictureId
            );
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
