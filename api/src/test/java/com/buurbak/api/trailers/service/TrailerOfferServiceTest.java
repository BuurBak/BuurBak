package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        // Then
        assertThatThrownBy(() -> trailerOfferService.getTrailerOffer(uuid))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void trailerOffersDoesNotExists() {
        // Given
        List<TrailerOffer> trailerOfferList = new ArrayList<>();
        // When
        when(trailerOfferService.getAllTrailerOffersInfo(any())).thenReturn(new PageImpl<>(trailerOfferList));

        Page<TrailerOffer> result = trailerOfferService.getAllTrailerOffersInfo(PageRequest.of(0, 20));

        // Then

        assertTrue(result.getContent().isEmpty());
    }

    @Test
    void trailerOffersExists() {
        //given
        EasyRandom easyRandom = new EasyRandom();
        List<TrailerOffer> trailerOfferList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            trailerOfferList.add(easyRandom.nextObject(TrailerOffer.class));
        }

        //when
        when(trailerOfferService.getAllTrailerOffersInfo(any())).thenReturn(new PageImpl<>(trailerOfferList));

        Page<TrailerOffer> result = trailerOfferService.getAllTrailerOffersInfo(PageRequest.of(0, 20));

        //then
        assertEquals(trailerOfferList, result.getContent());
    }

    @Test
    void trailerOfferPagesExists() {
        //given
        EasyRandom easyRandom = new EasyRandom();
        List<TrailerOffer> trailerOfferList = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            trailerOfferList.add(easyRandom.nextObject(TrailerOffer.class));
        }

        //when
        when(trailerOfferService.getAllTrailerOffersInfo(any())).thenReturn(new PageImpl<>(trailerOfferList));

        Page<TrailerOffer> result = trailerOfferService.getAllTrailerOffersInfo(Pageable.ofSize(20));
        //then
        assertEquals(5, result.getTotalPages());
    }
}