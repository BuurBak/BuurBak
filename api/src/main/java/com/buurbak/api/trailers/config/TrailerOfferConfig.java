package com.buurbak.api.trailers.config;

import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.trailers.repository.TrailerTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class TrailerOfferConfig {

    @Bean
    CommandLineRunner commandLineRunner(TrailerTypeRepository trailerTypeRepository){
        return args -> {
            TrailerType firstTrailerType = new TrailerType(
                    "Small"
            );
            TrailerType secondTrailerType = new TrailerType(
                    "Big"
            );
            trailerTypeRepository.saveAll(
                    List.of(firstTrailerType, secondTrailerType)
            );
        };
    }
}