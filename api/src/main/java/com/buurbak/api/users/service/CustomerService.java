package com.buurbak.api.users.service;

import com.buurbak.api.images.exception.ImageNotFoundException;
import com.buurbak.api.images.model.Image;
import com.buurbak.api.images.service.ImageService;
import com.buurbak.api.users.dto.PatchCustomerProfilePictureDTO;
import com.buurbak.api.users.exception.CustomerNotFoundException;
import com.buurbak.api.users.exception.ForbiddenException;
import com.buurbak.api.users.model.Customer;
import com.buurbak.api.users.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ImageService imageService;

    public Customer findByUsername(String name) throws CustomerNotFoundException {
        return customerRepository.findByEmail(name).orElseThrow(CustomerNotFoundException::new);
    }

    @Transactional
    public void setCustomerProfilePicture(UUID customerId, String username, PatchCustomerProfilePictureDTO patchCustomerProfilePictureDTO) throws CustomerNotFoundException, ForbiddenException, IllegalStateException, ImageNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);

        if (!username.equals(customer.getEmail())) {
            throw new ForbiddenException("You are not allowed to edit this account.");
        }

        if (patchCustomerProfilePictureDTO.getProfilePictureId() != null) {
            Image image = imageService.findById(patchCustomerProfilePictureDTO.getProfilePictureId());
            image.setUser(customer);
            customer.setProfilePictures(new ArrayList<>(List.of(image)));
        } else {
            customer.getProfilePictures().forEach(image -> image.setUser(null));
            customer.setProfilePictures(new ArrayList<>());
        }

        customerRepository.save(customer);
    }
}
