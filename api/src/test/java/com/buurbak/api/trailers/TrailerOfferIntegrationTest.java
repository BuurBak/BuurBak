package com.buurbak.api.trailers;

import com.buurbak.api.randomData.RandomDataGenerator;
import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.service.TrailerOfferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
public class TrailerOfferIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private RandomDataGenerator rdg;
    @Autowired
    private TrailerOfferService trailerOfferService;
    private ObjectMapper mapper;
    private String user = "lucabergman@yahoo.com";

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void addTrailerOffer() throws Exception {
        CreateTrailerOfferDTO randomTrailer = rdg.trailerOffer.nextObject(CreateTrailerOfferDTO.class);

        mvc.perform(MockMvcRequestBuilders
                        .post("/traileroffers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(randomTrailer))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateTrailerOffer() throws Exception {
        UUID trailerId = trailerOfferService.addTrailerOffer(rdg.trailerOffer.nextObject(CreateTrailerOfferDTO.class), user).getId();
        CreateTrailerOfferDTO randomTrailer = rdg.trailerOffer.nextObject(CreateTrailerOfferDTO.class);

        mvc.perform(MockMvcRequestBuilders
                        .put("/traileroffers/" + trailerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(randomTrailer))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTrailerOffer() throws Exception {
        UUID trailerId = trailerOfferService.addTrailerOffer(rdg.trailerOffer.nextObject(CreateTrailerOfferDTO.class), user).getId();

        mvc.perform(MockMvcRequestBuilders
                        .delete("/traileroffers/" + trailerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}