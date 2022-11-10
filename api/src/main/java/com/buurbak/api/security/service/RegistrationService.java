package com.buurbak.api.security.service;

import com.buurbak.api.email.service.ConfirmEmailService;
import com.buurbak.api.email.service.EmailSender;
import com.buurbak.api.security.dto.RegisterNewCustomerDTO;
import com.buurbak.api.security.exception.EmailConfirmationTokenAlreadyConfirmedException;
import com.buurbak.api.security.exception.EmailConfirmationTokenNotFoundException;
import com.buurbak.api.security.exception.EmailTakenException;
import com.buurbak.api.security.model.EmailConfirmationToken;
import com.buurbak.api.users.model.Address;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailConfirmationTokenService emailConfirmationTokenService;
    private final EmailSender emailSender;
    private final ConfirmEmailService confirmEmailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerService customerService;


    private final static String EMAIL_TAKEN_MESSAGE = "Email: %s already taken";


    public Customer registerNewCustomer(RegisterNewCustomerDTO registerNewCustomerDTO) throws EmailTakenException {
        if (appUserService.isEmailTaken(registerNewCustomerDTO.email())) {
            throw new EmailTakenException(String.format(EMAIL_TAKEN_MESSAGE, registerNewCustomerDTO.email()));
        }

        Customer customerToRegister = customerService.saveCustomer(
                new Customer(
                    registerNewCustomerDTO.email(),
                    bCryptPasswordEncoder.encode(registerNewCustomerDTO.password()),
                    registerNewCustomerDTO.name(),
                    null,
                    null,
                    registerNewCustomerDTO.number(),
                    new Address(
                            registerNewCustomerDTO.address().city(),
                            registerNewCustomerDTO.address().streetName(),
                            registerNewCustomerDTO.address().number(),
                            registerNewCustomerDTO.address().postalCode()
                    )
            )
        );

        EmailConfirmationToken emailConfirmationToken = emailConfirmationTokenService.saveEmailConfirmationToken(
                new EmailConfirmationToken(customerToRegister)
        );

        emailSender.send(
                registerNewCustomerDTO.email(),
                confirmEmailService.buildEmail(
                        registerNewCustomerDTO.name(),
                        System.getenv("EMAIL_LINK") + emailConfirmationToken.getId().toString()
                )
        );

        return customerToRegister;
    }


    public void confirmEmail(UUID tokenId) throws EmailConfirmationTokenNotFoundException, EmailConfirmationTokenAlreadyConfirmedException {
        EmailConfirmationToken emailConfirmationToken = emailConfirmationTokenService.findById(tokenId);

        if(emailConfirmationToken.isConfirmed()) {
                throw new EmailConfirmationTokenAlreadyConfirmedException(String.format("Email: %s has already been confirmed", emailConfirmationToken.getCustomer().getEmail()));
        }

        // TODO think about: should the email confirmation token expire?
        emailConfirmationToken.setConfirmed(true);
        emailConfirmationTokenService.saveEmailConfirmationToken(emailConfirmationToken);
        emailConfirmationToken.getCustomer().setEnabled(true);
        customerService.saveCustomer(emailConfirmationToken.getCustomer());
    }
}
