package com.buurbak.api.data.randomizers;

import org.jeasy.random.api.Randomizer;

public class Hallo123PasswordRandomizer implements Randomizer<String> {
    @Override
    public String getRandomValue() {
        // hallo123
        return "$2a$10$t2F/dROSQeLH3znBEQNPHO.X8aysGv8HTcglvxcMx0KvPJxCbKfQe";
    }

}