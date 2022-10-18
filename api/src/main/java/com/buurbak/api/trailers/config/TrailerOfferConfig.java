package com.buurbak.api.trailers.config;

import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.trailers.repository.TrailerTypeRepository;
import com.buurbak.api.trailers.model.TrailerOffer;

import com.buurbak.api.trailers.model.TrailerType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.List;


@Configuration
public class TrailerOfferConfig {

    @Bean
    CommandLineRunner commandLineRunner(TrailerOfferRepository repository, TrailerTypeRepository trailerTypeRepository){
        return args -> {
            TrailerType firstTrailerType = new TrailerType(
                    "Small"
            );

            TrailerOffer firstTrailerOffer = new TrailerOffer(
                    firstTrailerType,
                    250,
                    50,
                    100,
                    120,
                    1000,
                    "67DE1D",
                    LocalTime.of(8,00,00),
                    LocalTime.of(10,00,00),
                    LocalTime.of(18,00,00),
                    LocalTime.of(21,00,00),
                    "Heidelberglaan",
                    15,
                    false

            );

            TrailerType secondTrailerType = new TrailerType(
                    "Big"
            );

            TrailerOffer secondTrailerOffer = new TrailerOffer(
                    secondTrailerType,
                    1,
                    1,
                    1,
                    1,
                    1,
                    "8234DH",
                    LocalTime.of(8,00,00),
                    LocalTime.of(10,00,00),
                    LocalTime.of(18,00,00),
                    LocalTime.of(21,00,00),
                    "Padualaan",
                    20,
                    false
            );
            trailerTypeRepository.saveAll(
                    List.of(firstTrailerType, secondTrailerType)
            );

            repository.saveAll(
                    List.of(firstTrailerOffer, secondTrailerOffer)
            );
        };
    }
}