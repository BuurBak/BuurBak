package com.buurbak.api.reservations;

import com.buurbak.api.randomData.RandomDataGenerator;
import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.reservations.repository.ReservationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "lucabergman@yahoo.com")
@Slf4j
public class ReservationIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private RandomDataGenerator rdg;
    @Autowired
    private ReservationRepository reservationRepository;
    private ObjectMapper mapper;
    private UUID reservationId;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        reservationId = reservationRepository.save(rdg.reservation.nextObject(Reservation.class)).getId();
    }

    @Test
    void shouldAddReservation() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rdg.reservation.nextObject(ReservationDTO.class)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateReservation() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/reservations/" + reservationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rdg.reservation.nextObject(ReservationDTO.class)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteReservation() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/reservations/" + reservationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}