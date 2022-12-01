package com.buurbak.api.trailers;

import com.buurbak.api.trailers.controller.TrailerOfferController;
import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TrailerOfferController.class)
public class TrailerOfferIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Disabled
    @Test
    void addTrailerOffer() throws Exception {
        EasyRandom easyRandom = new EasyRandom();
        CreateTrailerOfferDTO trailerOfferDTO = easyRandom.nextObject(CreateTrailerOfferDTO.class);

        mvc.perform(MockMvcRequestBuilders
                .post("/traileroffer")
                .content(String.valueOf(trailerOfferDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}