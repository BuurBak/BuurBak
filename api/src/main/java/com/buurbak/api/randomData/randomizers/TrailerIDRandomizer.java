package com.buurbak.api.randomData.randomizers;

import com.buurbak.api.trailers.model.TrailerOffer;
import org.jeasy.random.api.Randomizer;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TrailerIDRandomizer implements Randomizer<UUID> {
    private final List<UUID> trailerIDs;

    public TrailerIDRandomizer(List<TrailerOffer> trailerOffers) {
        this.trailerIDs = trailerOffers.stream().map(TrailerOffer::getId).toList();
    }

    @Override
    public UUID getRandomValue() {
        return trailerIDs.get(new Random().nextInt(trailerIDs.size()));
    }
}