package com.buurbak.api;

import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class GenerateRandomData implements CommandLineRunner {
    private static final int CUSTOMERS_TO_GENERATE = 100;

    private CustomerRepository customerRepository;

    @Transactional
    public void run(String... args) {
        if (!System.getenv("RANDOM_DATA").equals("true")) {
            return;
        }

        // Constants
        LocalTime nine = LocalTime.of(9, 0);
        LocalTime five = LocalTime.of(17, 0);

        LocalDate today = LocalDate.now();

        EasyRandomParameters customerParamaters = new EasyRandomParameters()
                .seed(123L)
                .objectPoolSize(100)
                .randomizationDepth(5)
                .charset(StandardCharsets.UTF_8)
                .timeRange(nine, five)
                .dateRange(today.minusYears(60), today.minusYears(18))
                .stringLengthRange(5, 50)
                .collectionSizeRange(1, 4)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .ignoreRandomizationErrors(true)
                .bypassSetters(true);

        EasyRandom customerRandom = new EasyRandom(customerParamaters);

        // Generate Customers
        log.info("Generating customers");
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < CUSTOMERS_TO_GENERATE; i++) {
            customers.add(customerRandom.nextObject(Customer.class));
        }
        customerRepository.saveAll(customers);
    }
}
