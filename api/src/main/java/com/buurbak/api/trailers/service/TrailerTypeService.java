package com.buurbak.api.trailers.service;


import com.buurbak.api.trailers.exception.TrailerTypeNotFoundException;
import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.trailers.repository.TrailerTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrailerTypeService {
    final TrailerTypeRepository trailerTypeRepository;

    public TrailerType findByName(String name) throws TrailerTypeNotFoundException {
        return trailerTypeRepository.findByName(name).orElseThrow(TrailerTypeNotFoundException::new);
    }
}
