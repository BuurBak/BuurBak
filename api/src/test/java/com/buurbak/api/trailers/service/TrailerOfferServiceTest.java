package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.service.CustomerService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrailerOfferServiceTest {
    @Mock private TrailerOfferRepository trailerOfferRepository;
    private TrailerOfferService trailerOfferService;
    @Mock private TrailerTypeService trailerTypeService;
    @Mock private CustomerService customerService;


    @BeforeEach
    void setUp() {
        trailerOfferService = new TrailerOfferService(trailerOfferRepository, customerService, trailerTypeService);
    }

    @Test
    void addTrailerOffer() {
        EasyRandom easyRandom = new EasyRandom();
        CreateTrailerOfferDTO trailerOfferDTO = easyRandom.nextObject(CreateTrailerOfferDTO.class);

        trailerOfferService.addTrailerOffer(trailerOfferDTO, "lucabergman@yahoo.com");

        ArgumentCaptor<TrailerOffer> trailerOfferArgumentCaptor = ArgumentCaptor.forClass(TrailerOffer.class);

        verify(trailerOfferRepository).save(trailerOfferArgumentCaptor.capture());
        TrailerOffer capturedTrailerOffer = trailerOfferArgumentCaptor.getValue();

        assertThat(capturedTrailerOffer).hasFieldOrProperty("id");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("trailerType");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("owner");
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("length", trailerOfferDTO.getLength());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("height", trailerOfferDTO.getHeight());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("width", trailerOfferDTO.getWidth());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("weight", trailerOfferDTO.getWeight());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("capacity", trailerOfferDTO.getCapacity());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("licensePlateNumber", trailerOfferDTO.getLicensePlateNumber());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("pickUpTimeStart", trailerOfferDTO.getPickUpTimeStart());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("pickUpTimeEnd", trailerOfferDTO.getPickUpTimeEnd());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("dropOffTimeStart", trailerOfferDTO.getDropOffTimeStart());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("dropOffTimeEnd", trailerOfferDTO.getDropOffTimeEnd());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("location", trailerOfferDTO.getLocation());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("price", trailerOfferDTO.getPrice());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("available", trailerOfferDTO.isAvailable());
        assertThat(capturedTrailerOffer).hasFieldOrProperty("createdAt");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("updatedAt");
    }

    @Test
    void updateTrailerOffer() {
        EasyRandom easyRandom = new EasyRandom();
        TrailerOffer oldTrailerOffer = easyRandom.nextObject(TrailerOffer.class);
        CreateTrailerOfferDTO newTrailerOfferDTO = easyRandom.nextObject(CreateTrailerOfferDTO.class);

        when(trailerOfferRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(oldTrailerOffer));

        trailerOfferService.updateTrailerOffer(oldTrailerOffer.getId(), newTrailerOfferDTO);

        ArgumentCaptor<TrailerOffer> trailerOfferArgumentCaptor = ArgumentCaptor.forClass(TrailerOffer.class);

        verify(trailerOfferRepository).save(trailerOfferArgumentCaptor.capture());
        TrailerOffer capturedTrailerOffer = trailerOfferArgumentCaptor.getValue();

        assertThat(capturedTrailerOffer).hasFieldOrProperty("id");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("trailerType");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("owner");
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("length", newTrailerOfferDTO.getLength());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("height", newTrailerOfferDTO.getHeight());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("width", newTrailerOfferDTO.getWidth());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("weight", newTrailerOfferDTO.getWeight());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("capacity", newTrailerOfferDTO.getCapacity());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("licensePlateNumber", newTrailerOfferDTO.getLicensePlateNumber());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("pickUpTimeStart", newTrailerOfferDTO.getPickUpTimeStart());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("pickUpTimeEnd", newTrailerOfferDTO.getPickUpTimeEnd());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("dropOffTimeStart", newTrailerOfferDTO.getDropOffTimeStart());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("dropOffTimeEnd", newTrailerOfferDTO.getDropOffTimeEnd());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("location", newTrailerOfferDTO.getLocation());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("price", newTrailerOfferDTO.getPrice());
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("available", newTrailerOfferDTO.isAvailable());
        assertThat(capturedTrailerOffer).hasFieldOrProperty("createdAt");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("updatedAt");
    }
}