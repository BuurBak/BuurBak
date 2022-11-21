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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrailerOfferServiceTest {
    @Mock
    private TrailerOfferRepository trailerOfferRepository;

    @InjectMocks
    private TrailerOfferService trailerOfferService;

    @Test
    void shouldFindById() {
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
    void shouldNotFindById(){
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        // Then
        assertThrows(EntityNotFoundException.class, () -> trailerOfferService.getTrailerOffer(uuid));
        verify(trailerOfferRepository, times(1)).findById(uuid);
        verifyNoMoreInteractions(trailerOfferRepository);
    }

    @Test
    void shouldReturnEmptyPage() {
        // Given
        List<TrailerOffer> trailerOfferList = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 20);

        // When
        when(trailerOfferService.getAllTrailerOffers(any())).thenReturn(new PageImpl<>(trailerOfferList));

        Page<TrailerOffer> result = trailerOfferService.getAllTrailerOffers(pageable);

        // Then
        assertTrue(result.getContent().isEmpty());
        verify(trailerOfferRepository, times(1)).findAll(pageable);
        verifyNoMoreInteractions(trailerOfferRepository);
    }

    @Test
    void shouldReturnTrailerOfferPage() {
        // Given
        EasyRandom easyRandom = new EasyRandom();
        List<TrailerOffer> trailerOfferList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            trailerOfferList.add(easyRandom.nextObject(TrailerOffer.class));
        }
        Pageable pageable = PageRequest.of(0, 20);

        // When
        when(trailerOfferService.getAllTrailerOffers(any())).thenReturn(new PageImpl<>(trailerOfferList));

        Page<TrailerOffer> result = trailerOfferService.getAllTrailerOffers(pageable);

        // Then
        assertEquals(trailerOfferList, result.getContent());
        verify(trailerOfferRepository, times(1)).findAll(pageable);
        verifyNoMoreInteractions(trailerOfferRepository);
    }
}