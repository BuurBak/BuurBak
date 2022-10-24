package com.buurbak.api.users.controller;

import com.buurbak.api.users.converter.CustomerConverter;
import com.buurbak.api.users.dto.PrivateCustomerDTO;
import com.buurbak.api.users.dto.PublicCustomerDTO;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @GetMapping("self")
    @Operation(summary = "Get self")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
    })
    public PrivateCustomerDTO getCustomerSelf(Authentication authentication) {
        try {
            Customer customer = customerService.findByUsername(authentication.getName());
            return customerConverter.convertCustomerToPrivateCustomerDTO(customer);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find customer in database", exception);
        }
    }
}
