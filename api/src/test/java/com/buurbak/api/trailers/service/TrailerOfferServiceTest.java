package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.exception.AccessDeniedExeption;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class TrailerOfferServiceTest {
    @Mock private TrailerOfferRepository trailerOfferRepository;
    @Mock private TrailerTypeService trailerTypeService;
    @Mock private CustomerService customerService;

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

	@Test
	void addTrailerOffer() {
		EasyRandom easyRandom = new EasyRandom();
		CreateTrailerOfferDTO trailerOfferDTO = easyRandom.nextObject(CreateTrailerOfferDTO.class);

		trailerOfferService.addTrailerOffer(trailerOfferDTO, "lucabergman@yahoo.com");

		ArgumentCaptor<TrailerOffer> trailerOfferArgumentCaptor = ArgumentCaptor.forClass(TrailerOffer.class);

		verify(trailerOfferRepository).save(trailerOfferArgumentCaptor.capture());
		TrailerOffer capturedTrailerOffer = trailerOfferArgumentCaptor.getValue();

		assertThat(capturedTrailerOffer)
                .hasFieldOrProperty("id")
                .hasFieldOrProperty("trailerType")
                .hasFieldOrProperty("owner")
                .hasFieldOrPropertyWithValue("length", trailerOfferDTO.getLength())
		        .hasFieldOrPropertyWithValue("height", trailerOfferDTO.getHeight())
                .hasFieldOrPropertyWithValue("width", trailerOfferDTO.getWidth())
		        .hasFieldOrPropertyWithValue("weight", trailerOfferDTO.getWeight())
		        .hasFieldOrPropertyWithValue("capacity", trailerOfferDTO.getCapacity())
		        .hasFieldOrPropertyWithValue("licensePlateNumber", trailerOfferDTO.getLicensePlateNumber())
                .hasFieldOrPropertyWithValue("pickUpTimeStart", trailerOfferDTO.getPickUpTimeStart())
		        .hasFieldOrPropertyWithValue("pickUpTimeEnd", trailerOfferDTO.getPickUpTimeEnd())
                .hasFieldOrPropertyWithValue("dropOffTimeStart", trailerOfferDTO.getDropOffTimeStart())
                .hasFieldOrPropertyWithValue("dropOffTimeEnd", trailerOfferDTO.getDropOffTimeEnd())
                .hasFieldOrPropertyWithValue("latitude", trailerOfferDTO.getLatitude())
                .hasFieldOrPropertyWithValue("longitude", trailerOfferDTO.getLongitude())
                .hasFieldOrPropertyWithValue("price", trailerOfferDTO.getPrice())
		        .hasFieldOrPropertyWithValue("available", trailerOfferDTO.isAvailable())
		        .hasFieldOrProperty("createdAt")
		        .hasFieldOrProperty("updatedAt");

        assertNotEquals(trailerOfferDTO.getLatitude(), capturedTrailerOffer.getFakeLatitude());
        assertNotEquals(trailerOfferDTO.getLongitude(), capturedTrailerOffer.getFakeLongitude());
	}

    @Test
    void updateTrailerOffer() throws AccessDeniedExeption {
        EasyRandom easyRandom = new EasyRandom();
        TrailerOffer oldTrailerOffer = easyRandom.nextObject(TrailerOffer.class);
        CreateTrailerOfferDTO newTrailerOfferDTO = easyRandom.nextObject(CreateTrailerOfferDTO.class);

        when(trailerOfferRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(oldTrailerOffer));
        when(customerService.findByUsername(any())).thenReturn(oldTrailerOffer.getOwner());

        trailerOfferService.updateTrailerOffer(oldTrailerOffer.getId(), newTrailerOfferDTO, oldTrailerOffer.getOwner().getUsername());

        ArgumentCaptor<TrailerOffer> trailerOfferArgumentCaptor = ArgumentCaptor.forClass(TrailerOffer.class);

        verify(trailerOfferRepository).save(trailerOfferArgumentCaptor.capture());
        TrailerOffer capturedTrailerOffer = trailerOfferArgumentCaptor.getValue();

        assertThat(capturedTrailerOffer)
                .hasFieldOrProperty("id")
                .hasFieldOrProperty("trailerType")
                .hasFieldOrProperty("owner")
                .hasFieldOrPropertyWithValue("length", newTrailerOfferDTO.getLength())
                .hasFieldOrPropertyWithValue("height", newTrailerOfferDTO.getHeight())
                .hasFieldOrPropertyWithValue("width", newTrailerOfferDTO.getWidth())
                .hasFieldOrPropertyWithValue("weight", newTrailerOfferDTO.getWeight())
                .hasFieldOrPropertyWithValue("capacity", newTrailerOfferDTO.getCapacity())
                .hasFieldOrPropertyWithValue("licensePlateNumber", newTrailerOfferDTO.getLicensePlateNumber())
                .hasFieldOrPropertyWithValue("pickUpTimeStart", newTrailerOfferDTO.getPickUpTimeStart())
                .hasFieldOrPropertyWithValue("pickUpTimeEnd", newTrailerOfferDTO.getPickUpTimeEnd())
                .hasFieldOrPropertyWithValue("dropOffTimeStart", newTrailerOfferDTO.getDropOffTimeStart())
                .hasFieldOrPropertyWithValue("dropOffTimeEnd", newTrailerOfferDTO.getDropOffTimeEnd())
                .hasFieldOrPropertyWithValue("latitude", newTrailerOfferDTO.getLatitude())
                .hasFieldOrPropertyWithValue("longitude", newTrailerOfferDTO.getLongitude())
                .hasFieldOrPropertyWithValue("price", newTrailerOfferDTO.getPrice())
                .hasFieldOrPropertyWithValue("available", newTrailerOfferDTO.isAvailable())
                .hasFieldOrProperty("createdAt")
                .hasFieldOrProperty("updatedAt");

        assertNotEquals(newTrailerOfferDTO.getLatitude(), capturedTrailerOffer.getFakeLatitude());
        assertNotEquals(newTrailerOfferDTO.getLongitude(), capturedTrailerOffer.getFakeLongitude());
    }
}