package com.buurbak.api.trailers.controller;

import com.buurbak.api.trailers.dto.AccessoireNameDTO;
import com.buurbak.api.trailers.service.AccessoireService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("traileraccessoires")
public class AccessoireController {
    private AccessoireService accessoireService;
//    @PostMapping
//    public void postAccessoire(@Valid @RequestBody AccessoireDTO requestDTO){
//        System.out.println(requestDTO);
//        accessoireService.saveAccessoire(requestDTO);
//    }

    @PostMapping
    public void postAccessoire(@Valid @RequestBody AccessoireNameDTO accessoireNameDTO){
        accessoireService.saveAccessoire(accessoireNameDTO);
    }

    @GetMapping
    public List<AccessoireNameDTO> getAccossoire(){
        return accessoireService.getAllAccossoire();
    }

    @DeleteMapping(path = "/{identifier}")
    public void deleteAccessoire(@PathVariable String identifier){
        try {
            accessoireService.deleteAccessoire(identifier);
        } catch (EntityNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
