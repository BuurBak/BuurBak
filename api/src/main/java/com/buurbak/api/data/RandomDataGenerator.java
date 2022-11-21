package com.buurbak.api.data;

import com.buurbak.api.data.randomizers.Hallo123PasswordRandomizer;
import com.buurbak.api.data.randomizers.TrailerDimensionRandomizer;
import com.buurbak.api.data.randomizers.TrailerOwnerRandomizer;
import com.buurbak.api.data.randomizers.TrailerTypeRandomizer;
import com.buurbak.api.security.model.AppUser;
import com.buurbak.api.trailers.model.TrailerOffer;
import com.buurbak.api.trailers.model.TrailerType;
import com.buurbak.api.trailers.repository.TrailerOfferRepository;
import com.buurbak.api.trailers.repository.TrailerTypeRepository;
import com.buurbak.api.users.model.Address;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.CreditCardNumberRandomizer;
import org.jeasy.random.randomizers.EmailRandomizer;
import org.jeasy.random.randomizers.FullNameRandomizer;
import org.jeasy.random.randomizers.misc.SkipRandomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Slf4j
public class RandomDataGenerator implements CommandLineRunner {
    private static final int CUSTOMERS_TO_GENERATE = 50;
    private static final int TRAILER_OFFERS_TO_GENERATE = 200;

    private final CustomerRepository customerRepository;
    private final TrailerOfferRepository trailerOfferRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TrailerTypeRepository trailerTypeRepository;

    @Value("${data.generate-random-data:false}")
    private boolean GENERATE_RANDOM_DATA;

    public void run(String... args) {
        if (!GENERATE_RANDOM_DATA) {
            return;
        }

        // Constants
        LocalTime nine = LocalTime.of(9, 0);
        LocalTime five = LocalTime.of(17, 0);

        LocalDate today = LocalDate.now();



        // Generate Customer Paramater
        EasyRandomParameters customerParameters = new EasyRandomParameters()
                .randomize(
                        FieldPredicates.named("name")
                                .and(FieldPredicates.ofType(String.class))
                                .and(FieldPredicates.inClass(Customer.class)),
                        new FullNameRandomizer()
                )
                .randomize(
                        FieldPredicates.named("email")
                                .and(FieldPredicates.inClass(AppUser.class)),
                        new EmailRandomizer()
                )
                .randomize(
                        FieldPredicates.named("password")
                                .and(FieldPredicates.ofType(String.class))
                                .and(FieldPredicates.inClass(AppUser.class)),
                        new Hallo123PasswordRandomizer()
                )
                .randomize(
                        FieldPredicates.named("iban")
                                .and(FieldPredicates.inClass(Customer.class)),
                        new CreditCardNumberRandomizer()
                )
                .randomize(
                        FieldPredicates.named("deleted")
                                .and(FieldPredicates.inClass(AppUser.class)),
                        new SkipRandomizer())
                .randomize(
                        FieldPredicates.named("locked")
                                .and(FieldPredicates.inClass(AppUser.class)),
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
        EasyRandom customerRandom = new EasyRandom(customerParameters);



        // Generate customers
        log.info("Generating customers");
        // Standard customer
        Customer lucaBergman = new Customer(
                "lucabergman@yahoo.com",
                bCryptPasswordEncoder.encode("hallo123"),
                "Luca Bergman",
                LocalDate.of(2001, 3, 10),
                "This is an IBAN",
                "06-12345678",
                new Address(
                        "Amsterdam",
                        "Oudezijds Voorburgwal",
                        "258",
                        "1012 GK"
                )
        );
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < CUSTOMERS_TO_GENERATE; i++) {
            customers.add(customerRandom.nextObject(Customer.class));
        }
        customers.add(lucaBergman);
        Iterable<Customer> customerIterable = customerRepository.saveAll(customers);
        customers = StreamSupport
                .stream(customerIterable.spliterator(), false)
                .collect(Collectors.toList());
        log.info("Generated: " + CUSTOMERS_TO_GENERATE + " customers");



        // Generate Trailer types
        List<TrailerType> trailerTypes = new ArrayList<>();
        trailerTypes.add(new TrailerType("Small"));
        trailerTypes.add(new TrailerType("Medium"));
        trailerTypes.add(new TrailerType("Big"));
        trailerTypeRepository.saveAll(trailerTypes);



        // Build TrailerOfferParamaters
        EasyRandomParameters trailerOfferParameters = new EasyRandomParameters()
                .randomize(FieldPredicates.named("owner")
                                .and(FieldPredicates.inClass(TrailerOffer.class)),
                        new TrailerOwnerRandomizer(customers))
                .randomize(FieldPredicates.named("trailerType")
                                .and(FieldPredicates.inClass(TrailerOffer.class)),
                        new TrailerTypeRandomizer(trailerTypes))
                .randomize(FieldPredicates.named("length")
                                .and(FieldPredicates.inClass(TrailerOffer.class)),
                        new TrailerDimensionRandomizer())
                .randomize(FieldPredicates.named("width")
                                .and(FieldPredicates.inClass(TrailerOffer.class)),
                        new TrailerDimensionRandomizer())
                .randomize(FieldPredicates.named("height")
                                .and(FieldPredicates.inClass(TrailerOffer.class)),
                        new TrailerDimensionRandomizer())
                .randomize(FieldPredicates.named("weight")
                                .and(FieldPredicates.inClass(TrailerOffer.class)),
                        new TrailerDimensionRandomizer())
                .randomize(FieldPredicates.named("capacity")
                                .and(FieldPredicates.inClass(TrailerOffer.class)),
                        new TrailerDimensionRandomizer())
                .randomize(
                        FieldPredicates.named("deleted")
                                .and(FieldPredicates.inClass(AppUser.class)),
                        new SkipRandomizer())
                .seed(123L)
                .objectPoolSize(100)
                .randomizationDepth(5)
                .charset(StandardCharsets.UTF_8)
                .timeRange(nine, five)
                .stringLengthRange(5, 50)
                .collectionSizeRange(1, 4)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .ignoreRandomizationErrors(true)
                .bypassSetters(true);
        EasyRandom trailerRandom = new EasyRandom(trailerOfferParameters);



        // Generate trailer offers
        log.info("Generating trailers");
        List<TrailerOffer> trailerOffers = new ArrayList<>();
        for (int i = 0; i < TRAILER_OFFERS_TO_GENERATE; i++) {
            trailerOffers.add(trailerRandom.nextObject(TrailerOffer.class));
        }
        trailerOfferRepository.saveAll(trailerOffers);
        log.info("Generated: " + TRAILER_OFFERS_TO_GENERATE + " trailer offers");



        // TODO generate user profile pictures and trailer pictures
    }
}
