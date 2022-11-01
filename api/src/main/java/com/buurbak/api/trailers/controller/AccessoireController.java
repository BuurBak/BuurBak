package com.buurbak.api.trailers.controller;

import com.buurbak.api.trailers.dto.AccessoireNameDTO;
import com.buurbak.api.trailers.exception.TrailerAccessoireAlreadyExistsException;
import com.buurbak.api.trailers.exception.TrailerAccessoireNotFoundException;
import com.buurbak.api.trailers.service.AccessoireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("traileraccessoires")
public class AccessoireController {
    private final AccessoireService accessoireService;
//    @PostMapping
//    public void postAccessoire(@Valid @RequestBody AccessoireDTO requestDTO){
//        System.out.println(requestDTO);
//        accessoireService.saveAccessoire(requestDTO);
//    }


    @Operation(summary = "Add accessoire")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "409", description = "Already Exists", content = @Content)
    })
    @PostMapping
    public void postAccessoire(@Valid @RequestBody AccessoireNameDTO accessoireNameDTO) {
        try {
            accessoireService.saveAccessoire(accessoireNameDTO);
        } catch (TrailerAccessoireAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @Operation(summary = "Get accossoires list")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @GetMapping
    public List<AccessoireNameDTO> getAccossoire(){
        return accessoireService.getAllAccossoire();
    }


    @Operation(summary = "Delete accossoire")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Accessoire not found", content = @Content)
    })
    @DeleteMapping(path = "/{identifier}")
    public void deleteAccessoire(@PathVariable String identifier) {
        try {
            accessoireService.deleteAccessoire(identifier);
        } catch (TrailerAccessoireNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
