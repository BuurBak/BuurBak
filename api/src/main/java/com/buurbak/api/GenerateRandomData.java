package com.buurbak.api;

import com.buurbak.api.config.randomizers.Hallo123PasswordRandomizer;
import com.buurbak.api.security.model.User;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import com.github.javafaker.Bool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.CreditCardNumberRandomizer;
import org.jeasy.random.randomizers.EmailRandomizer;
import org.jeasy.random.randomizers.FullNameRandomizer;
import org.jeasy.random.randomizers.PasswordRandomizer;
import org.jeasy.random.randomizers.misc.SkipRandomizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
                .randomize(
                        FieldPredicates.named("name")
                                .and(FieldPredicates.ofType(String.class))
                                .and(FieldPredicates.inClass(Customer.class)),
                        new FullNameRandomizer()
                )
                .randomize(
                        FieldPredicates.named("email")
                                .and(FieldPredicates.inClass(User.class)),
                        new EmailRandomizer()
                )
                .randomize(
                        FieldPredicates.named("password")
                                .and(FieldPredicates.ofType(String.class))
                                .and(FieldPredicates.inClass(User.class)),
                        new Hallo123PasswordRandomizer()
                )
                .randomize(
                        FieldPredicates.named("iban")
                                .and(FieldPredicates.inClass(Customer.class)),
                        new CreditCardNumberRandomizer()
                )
                .randomize(
                        FieldPredicates.named("deleted")
                                .and(FieldPredicates.inClass(User.class)),
                        new SkipRandomizer())
                .randomize(
                        FieldPredicates.named("locked")
                                .and(FieldPredicates.inClass(User.class)),
                        new SkipRandomizer())
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

        // standard customer
        Customer lucaBergman = new Customer(
                "lucabergman@yahoo.com",
                bCryptPasswordEncoder.encode("hallo123"),
                "Luca Bergman",
                LocalDate.of(2001, 3, 10),
                "This is an IBAN",
                "Amsterdam, de Wallen 3"
        );

        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < CUSTOMERS_TO_GENERATE; i++) {
            customers.add(customerRandom.nextObject(Customer.class));
        }

        customers.add(lucaBergman);
        customerRepository.saveAll(customers);
        log.info("Generated: " + CUSTOMERS_TO_GENERATE + " customers");

    }
}
