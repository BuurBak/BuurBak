package com.buurbak.api.users.controller;

import com.buurbak.api.images.exception.ImageNotFoundException;
import com.buurbak.api.users.dto.GetPrivateCustomerDTO;
import com.buurbak.api.users.dto.PatchCustomerProfilePictureDTO;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.exception.ForbiddenException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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
    public GetPrivateCustomerDTO getCustomerSelf(Authentication authentication) {
        try {
            Customer customer = customerService.findByUsername(authentication.getName());

            return new GetPrivateCustomerDTO(
                    customer.getId(),
                    customer.getEmail(),
                    customer.getName(),
                    customer.getDateOfBirth(),
                    customer.getIban(),
                    customer.getAddress(),
                    customer.getProfilePictures().size() > 0 ? customer.getProfilePictures().stream().toList().get(0).getPublicUrl() : null
            );
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find customer in database", e);
        }
    }

    @PatchMapping("/{id}/profile-picture")
    @Operation(summary = "Update user's profile picture")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "403", description = "You are not allowed to edit this account", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
    })
    public void patchCustomer(@PathVariable UUID id, Authentication authentication, @Valid @RequestBody PatchCustomerProfilePictureDTO patchCustomerProfilePictureDTO) {
        try {
            customerService.setCustomerProfilePicture(id, authentication.getName(), patchCustomerProfilePictureDTO);
        } catch (ImageNotFoundException | CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
