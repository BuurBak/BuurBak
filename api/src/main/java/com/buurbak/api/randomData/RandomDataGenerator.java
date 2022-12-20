package com.buurbak.api.randomData;

import com.buurbak.api.randomData.randomizers.*;
import com.buurbak.api.reservations.dto.ReservationDTO;
import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.security.model.Role;
import com.buurbak.api.security.service.RoleService;
import com.buurbak.api.trailers.dto.CreateTrailerOfferDTO;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;

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
    private final RoleService roleService;

    @Value("${data.generate-random-data:false}")
    private boolean GENERATE_RANDOM_DATA;

    public EasyRandom customer;
    public EasyRandom trailerOffer;
    public EasyRandom reservation;

    public void run(String... args) {
        if (!GENERATE_RANDOM_DATA) {
            return;
        }

        // Constants
        LocalTime nine = LocalTime.of(9, 0);
        LocalTime five = LocalTime.of(17, 0);
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate nextWeek = LocalDate.now().plusWeeks(1);
        Role userRole = roleService.findByName("USER");

        // Build Customer
        customer = new EasyRandom(new EasyRandomParameters()
                .randomize(named("name").and(inClass(Customer.class)), new FullNameRandomizer())
                .randomize(named("email"), new EmailRandomizer())
                .randomize(named("password"), new Hallo123PasswordRandomizer())
                .randomize(named("iban"), new CreditCardNumberRandomizer())
                .randomize(named("roles"), new UserRoleRandomizer(userRole))
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
                .bypassSetters(true));

        // Generate customers
        log.info("Generating customers");
        // Standard customer
        Customer lucaBergman = new Customer(
                "lucabergman@yahoo.com",
                bCryptPasswordEncoder.encode("hallo123"),
                Collections.singletonList(userRole),
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
            customers.add(customer.nextObject(Customer.class));
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

        // Build TrailerOffer
        trailerOffer = new EasyRandom(new EasyRandomParameters()
                .randomize(named("id"), new SkipRandomizer())
                .randomize(named("owner"), new TrailerOwnerRandomizer(customers))
                .randomize(named("trailerType").and(inClass(TrailerOffer.class)), new TrailerTypeRandomizer())
                .randomize(named("trailerType").and(inClass(CreateTrailerOfferDTO.class)), new TrailerTypeStringRandomizer())
                .randomize(named("length"), new TrailerDimensionRandomizer())
                .randomize(named("height"), new TrailerDimensionRandomizer())
                .randomize(named("width"), new TrailerDimensionRandomizer())
                .randomize(named("weight"), new TrailerDimensionRandomizer())
                .randomize(named("capacity"), new TrailerDimensionRandomizer())
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
                .bypassSetters(true));

        // Generate trailer offers
        log.info("Generating trailers");
        List<TrailerOffer> trailerOffers = new ArrayList<>();
        for (int i = 0; i < TRAILER_OFFERS_TO_GENERATE; i++) {
            trailerOffers.add(trailerOffer.nextObject(TrailerOffer.class));
        }
        Iterable<TrailerOffer> trailerOfferIterable = trailerOfferRepository.saveAll(trailerOffers);
        trailerOffers = StreamSupport
                .stream(trailerOfferIterable.spliterator(), false)
                .collect(Collectors.toList());
        log.info("Generated: " + TRAILER_OFFERS_TO_GENERATE + " trailer offers");

        // TODO generate user profile pictures and trailer pictures

        // Build Reservation
        reservation = new EasyRandom(new EasyRandomParameters()
                .randomize(named("id"), new SkipRandomizer())
                .randomize(named("renter"), new TrailerOwnerRandomizer(customers))
                .randomize(named("trailer").and(inClass(Reservation.class)), new TrailerOfferRandomizer(trailerOffers))
                .randomize(named("trailerId").and(inClass(ReservationDTO.class)), new TrailerIDRandomizer(trailerOffers))
                .randomize(named("confirmed"), () -> false)
                .randomize(named("confirmedAt"), () -> null)
                .stringLengthRange(5, 50)
                .timeRange(nine, five)
                .dateRange(tomorrow, nextWeek)
                .charset(StandardCharsets.UTF_8)
                .ignoreRandomizationErrors(false)
                .bypassSetters(true));
    }
}
