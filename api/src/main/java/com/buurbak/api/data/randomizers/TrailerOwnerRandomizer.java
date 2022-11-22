package com.buurbak.api.data.randomizers;

import com.buurbak.api.users.model.Customer;
import org.jeasy.random.api.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrailerOwnerRandomizer implements Randomizer<Customer> {
    private final List<Customer> customers;

    public TrailerOwnerRandomizer(List<Customer> customers) {
        // only half of the customers will rent out trailers
        this.customers
                = new ArrayList<>(customers.subList(0, (customers.size()) / 2));
    }

    @Override
    public Customer getRandomValue() {
        return customers.get(new Random().nextInt(customers.size()));
    }
}