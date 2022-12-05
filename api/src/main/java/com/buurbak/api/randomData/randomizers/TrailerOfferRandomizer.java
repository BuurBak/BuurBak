package com.buurbak.api.randomData.randomizers;

import com.buurbak.api.trailers.model.TrailerOffer;
import org.jeasy.random.api.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrailerOfferRandomizer implements Randomizer<TrailerOffer> {
    private final List<TrailerOffer> trailerOffers;

    public TrailerOfferRandomizer(List<TrailerOffer> trailerOffers) {
        this.trailerOffers = new ArrayList<>(trailerOffers);
    }

    @Override
    public TrailerOffer getRandomValue() {
        return trailerOffers.get(new Random().nextInt(trailerOffers.size()));
    }
}