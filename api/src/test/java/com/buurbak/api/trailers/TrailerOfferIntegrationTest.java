package com.buurbak.api.trailers;

import com.buurbak.api.randomData.RandomDataGenerator;
import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
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
    private TrailerOfferRepository trailerOfferRepository;
    @Autowired
    private CustomerService customerService;
    private ObjectMapper mapper;
    private UUID trailerId;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Customer customer = customerService.findByUsername("lucabergman@yahoo.com");
        TrailerOffer trailerOffer = rdg.trailerOffer.nextObject(TrailerOffer.class);
        trailerOffer.setCapacity(696);
        trailerOffer.setOwner(customer);
        trailerOffer = trailerOfferRepository.save(trailerOffer);
        trailerId = trailerOffer.getId();
    }

    @Test
    void shouldAddTrailerOffer() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/traileroffers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rdg.trailerOffer.nextObject(CreateTrailerOfferDTO.class)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateTrailerOffer() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/traileroffers/" + trailerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rdg.trailerOffer.nextObject(CreateTrailerOfferDTO.class)))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteTrailerOffer() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/traileroffers/" + trailerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}