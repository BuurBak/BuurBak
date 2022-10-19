package com.buurbak.api.trailers.service;


import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.trailers.repository.TrailerTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrailerTypeService {
    final TrailerTypeRepository<TrailerType> trailerTypeRepository;

    public TrailerType findByName(String name){
        return trailerTypeRepository.findByName(name);
    }
}
