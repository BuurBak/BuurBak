package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

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
//        EasyRandom easyRandom = new EasyRandom();
//        TrailerType trailerType = easyRandom.nextObject(TrailerType.class);

        CreateTrailerOfferDTO trailerOffer = new CreateTrailerOfferDTO(
                "Small",
                120,
                245,
                650,
                1000,
                300,
                "AB-C12-34",
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(17, 0),
                LocalTime.of(18, 0),
                "Amersfoort",
                20.00,
                true);

        trailerOfferService.addTrailerOffer(trailerOffer, "lucabergman@yahoo.com");

        ArgumentCaptor<TrailerOffer> trailerOfferArgumentCaptor = ArgumentCaptor.forClass(TrailerOffer.class);

        verify(trailerOfferRepository).save(trailerOfferArgumentCaptor.capture());
        TrailerOffer capturedTrailerOffer = trailerOfferArgumentCaptor.getValue();

        assertThat(capturedTrailerOffer).hasFieldOrProperty("id");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("trailerType");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("owner");
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("length", 120);
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("height", 245);
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("width", 650);
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("weight", 1000);
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("capacity", 300);
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("licensePlateNumber", "AB-C12-34");
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("pickUpTimeStart", LocalTime.of(8, 0));
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("pickUpTimeEnd", LocalTime.of(9, 0));
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("dropOffTimeStart", LocalTime.of(17, 0));
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("dropOffTimeEnd", LocalTime.of(18, 0));
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("location", "Amersfoort");
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("price", 20.00);
        assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("available", true);
        assertThat(capturedTrailerOffer).hasFieldOrProperty("createdAt");
        assertThat(capturedTrailerOffer).hasFieldOrProperty("updatedAt");
    }

    @Test
    void updateTrailerOffer() {
    }
}