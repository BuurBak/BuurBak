package com.buurbak.api.users.converter;

import com.buurbak.api.users.dto.PrivateCustomerDTO;
import com.buurbak.api.users.dto.PublicCustomerDTO;
import com.buurbak.api.users.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public PublicCustomerDTO convertCustomerToPublicCustomerDTO (Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, PublicCustomerDTO.class);
    }

    public PrivateCustomerDTO convertCustomerToPrivateCustomerDTO (Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, PrivateCustomerDTO.class);
    }
}
