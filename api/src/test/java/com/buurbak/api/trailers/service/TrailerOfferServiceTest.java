package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrailerOfferServiceTest {
    @Mock
    private TrailerOfferRepository trailerOfferRepository;

    @InjectMocks
    private TrailerOfferService trailerOfferService;

    @Test
    void trailerOfferExistsById() {
        // Given
        EasyRandom easyRandom = new EasyRandom();
        TrailerOffer trailerOffer = easyRandom.nextObject(TrailerOffer.class);
        trailerOffer.setId(UUID.randomUUID());

        // When
        when(trailerOfferRepository.findById(trailerOffer.getId())).thenReturn(Optional.of(trailerOffer));

        TrailerOffer result = trailerOfferService.getTrailerOffer(trailerOffer.getId());

        // Then
        assertEquals(trailerOffer, result);
        verify(trailerOfferRepository, times(1)).findById(trailerOffer.getId());
        verifyNoMoreInteractions(trailerOfferRepository);
    }

    @Test
    void trailerOfferDoesNotExistsById(){
        //Given
        UUID uuid = UUID.randomUUID();

        // When
        // Then
        assertThatThrownBy(() -> trailerOfferService.getTrailerOffer(uuid))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @Disabled
    void trailerOffersDoesNotExists() {
        //given

        //when

        //then
    }

    @Test
    @Disabled
    void trailerOffersExists() {
        //given

        //when

        //then
    }
}