package com.buurbak.api.config.randomizers;

import org.jeasy.random.api.Randomizer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TrailerDimensionRandomizer implements Randomizer<Integer> {
    private final List<Integer> trailerDimensions = Arrays.asList(100, 120, 200, 220, 300, 400, 450);

    @Override
    public Integer getRandomValue() {
        return trailerDimensions.get(new Random().nextInt(trailerDimensions.size()));
    }
}