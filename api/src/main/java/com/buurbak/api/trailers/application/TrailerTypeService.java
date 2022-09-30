package com.buurbak.api.trailers.application;


import com.buurbak.api.trailers.data.TrailerTypeRepository;
import com.buurbak.api.trailers.domain.TrailerType;
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
