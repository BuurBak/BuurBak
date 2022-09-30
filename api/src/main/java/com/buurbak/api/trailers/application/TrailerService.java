package com.buurbak.api.trailers.application;

import com.buurbak.api.trailers.data.TrailerRepository;
import com.buurbak.api.trailers.domain.Trailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrailerService {
    final TrailerRepository<Trailer> trailerRepository;

    @Autowired
    public TrailerService(TrailerRepository<Trailer> trailerRepository) {
        this.trailerRepository = trailerRepository;
    }

    public Trailer getTrailer(UUID id){
        return trailerRepository.getOneTrailer(id);
    }

    public List<Trailer> getAllTrailers(){
        return trailerRepository.getAllTrailers();
    }
}
