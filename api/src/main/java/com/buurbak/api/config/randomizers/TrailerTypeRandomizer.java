package com.buurbak.api.config.randomizers;

import com.buurbak.api.trailers.model.TrailerType;
import lombok.AllArgsConstructor;
import org.jeasy.random.api.Randomizer;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class TrailerTypeRandomizer implements Randomizer<TrailerType> {
    private final List<TrailerType> trailerTypes;

    @Override
    public TrailerType getRandomValue() {
        return trailerTypes.get(new Random().nextInt(trailerTypes.size()));
    }
}