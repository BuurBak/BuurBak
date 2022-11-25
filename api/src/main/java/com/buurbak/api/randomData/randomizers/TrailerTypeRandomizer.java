package com.buurbak.api.randomData.randomizers;

import com.buurbak.api.trailers.model.TrailerType;
import lombok.AllArgsConstructor;
import org.jeasy.random.api.Randomizer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class TrailerTypeRandomizer implements Randomizer<TrailerType> {
    private final List<TrailerType> trailerTypes = Arrays.asList(
            new TrailerType("Small"),
            new TrailerType("Medium"),
            new TrailerType("Big")
    );

    @Override
    public TrailerType getRandomValue() {
        return trailerTypes.get(new Random().nextInt(trailerTypes.size()));
    }
}