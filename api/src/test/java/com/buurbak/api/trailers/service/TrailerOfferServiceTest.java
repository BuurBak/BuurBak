package com.buurbak.api.trailers.service;

import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.service.CustomerService;
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

		assertThat(capturedTrailerOffer).hasFieldOrProperty("id");
		assertThat(capturedTrailerOffer).hasFieldOrProperty("trailerType");
		assertThat(capturedTrailerOffer).hasFieldOrProperty("owner");
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("length", trailerOfferDTO.getLength());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("height", trailerOfferDTO.getHeight());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("width", trailerOfferDTO.getWidth());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("weight", trailerOfferDTO.getWeight());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("capacity", trailerOfferDTO.getCapacity());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("licensePlateNumber",
				trailerOfferDTO.getLicensePlateNumber());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("pickUpTimeStart",
				trailerOfferDTO.getPickUpTimeStart());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("pickUpTimeEnd",
				trailerOfferDTO.getPickUpTimeEnd());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("dropOffTimeStart",
				trailerOfferDTO.getDropOffTimeStart());
		assertThat(capturedTrailerOffer).hasFieldOrPropertyWithValue("dropOffTimeEnd",
				trailerOfferDTO.getDropOffTimeEnd());
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