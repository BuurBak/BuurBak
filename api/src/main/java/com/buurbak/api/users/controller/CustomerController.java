package com.buurbak.api.users.controller;

import com.buurbak.api.users.controller.specifcation.NotDeletedCustomerSpecification;
import com.buurbak.api.users.converter.CustomerConverter;
import com.buurbak.api.users.dto.PrivateCustomerDTO;
import com.buurbak.api.users.dto.PublicCustomerDTO;
import com.buurbak.api.users.dto.UpdateCustomerDTO;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import com.buurbak.api.users.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final CustomerConverter customerConverter;

    @GetMapping("self")
    @Operation(summary = "Get self")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
    })
    public PrivateCustomerDTO getCustomerSelf(NotDeletedCustomerSpecification specification, Authentication authentication) {
        try {
            Customer customer = customerService.findByUsername(authentication.getName());
            return customerConverter.convertCustomerToPrivateCustomerDTO(customer);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find customer in database", exception);
        }
    }

    @GetMapping
    @Operation(summary = "Get all customers")
    @Parameters({
            @Parameter(name = "email")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
    })
    public Page<PublicCustomerDTO> getAllCustomers(
            NotDeletedCustomerSpecification specification,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Customer> customerPage = customerService.findAll(specification, pageable);
        return customerConverter.convertCustomerPageToPublicCustomerDTOPage(customerPage);
    }

    // Open api documentation is found above
    @GetMapping(params = { "email" })
    public Page<PublicCustomerDTO> filterCustomersByEmail(
            @Spec(path = "email", spec = Equal.class) NotDeletedCustomerSpecification specification,
            @PageableDefault(size = 1, sort = "email") Pageable pageable
    ) {
        Page<Customer> customerPage = customerService.findAll(specification, pageable);
        return customerConverter.convertCustomerPageToPublicCustomerDTOPage(customerPage);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateCustomerDTO updateCustomerDTO) {
        try {
            customerService.updateUser(id, updateCustomerDTO);
        }
        catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find user in database", e);
        }
    }

//    @Operation(summary = "Delete customer")
//    @ApiResponses({
//            @
//    })

//    @DeleteMapping void deleteCustomer(UUID id) {
//        if(!customerService.getCustomer(id)) {
//            throw new CustomerNotFoundException("Customer with id " + id + " does not exist");
//        }
//
//        CustomerRepository
//    }
}
