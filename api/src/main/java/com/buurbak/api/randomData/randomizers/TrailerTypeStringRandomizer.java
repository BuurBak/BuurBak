package com.buurbak.api.randomData.randomizers;

import lombok.AllArgsConstructor;
import org.jeasy.random.api.Randomizer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class TrailerTypeStringRandomizer implements Randomizer<String> {
    private final List<String> trailerTypes = Arrays.asList(
            "Small",
            "Medium",
            "Big"
    );

    @Override
    public String getRandomValue() {
        return trailerTypes.get(new Random().nextInt(trailerTypes.size()));
    }
}