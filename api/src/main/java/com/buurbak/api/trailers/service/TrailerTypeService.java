package com.buurbak.api.trailers.service;


import com.buurbak.api.trailers.repository.TrailerTypeRepository;
import com.buurbak.api.trailers.model.TrailerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrailerTypeService {
    final TrailerTypeRepository<TrailerType> trailerTypeRepository;

    @Autowired
    public TrailerTypeService(TrailerTypeRepository<TrailerType> trailerTypeRepository) {
        this.trailerTypeRepository = trailerTypeRepository;
    }

    public TrailerType findTrailerTypeByName(String name){
        return trailerTypeRepository.findByName(name);
    }
}
