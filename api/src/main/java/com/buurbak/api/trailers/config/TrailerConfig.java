package com.buurbak.api.trailers.config;

import com.buurbak.api.trailers.data.TrailerRepository;
import com.buurbak.api.trailers.data.TrailerTypeRepository;
import com.buurbak.api.trailers.domain.Trailer;

import com.buurbak.api.trailers.domain.TrailerType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class TrailerConfig {

    @Bean
    CommandLineRunner commandLineRunner(TrailerRepository repository, TrailerTypeRepository trailerTypeRepository){
        return args -> {
            TrailerType firstTrailerType = new TrailerType(
                    "Small"
            );

            Trailer firstTrailer = new Trailer(
                    firstTrailerType,
                    250,
                    50,
                    100,
                    120,
                    1000,
                    "67DE1D"
            );

            TrailerType secondTrailerType = new TrailerType(
                    "Big"
            );

            Trailer secondTrailer = new Trailer(
                    secondTrailerType,
                    1,
                    1,
                    1,
                    1,
                    1,
                    "8234DH"
            );
            trailerTypeRepository.saveAll(
                    List.of(firstTrailerType, secondTrailerType)
            );

            repository.saveAll(
                    List.of(firstTrailer, secondTrailer)
            );
        };
    }
}

