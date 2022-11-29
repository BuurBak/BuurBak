package com.buurbak.api.users.converter;

import com.buurbak.api.reservations.dto.ReturnReservationDTO;
import com.buurbak.api.reservations.model.Reservation;
import com.buurbak.api.users.dto.PrivateCustomerDTO;
import com.buurbak.api.users.dto.PublicCustomerDTO;
import com.buurbak.api.users.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Page<PublicCustomerDTO> convertCustomerPageToPublicCustomerDTOPage (Page<Customer> customerPage) {
        List<PublicCustomerDTO> publicCustomerDTOList = customerPage
                .stream()
                .map(this::convertCustomerToPublicCustomerDTO)
                .toList();
        return new PageImpl<>(publicCustomerDTOList, customerPage.getPageable(), customerPage.getTotalElements());
    }

    public ReturnReservationDTO convertReservationToReservationDTO (Reservation reservation) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reservation, ReturnReservationDTO.class);
    }

    public Page<ReturnReservationDTO> convertReservationPageToReservationDTOPage(Page<Reservation> reservationPage){
        List<ReturnReservationDTO> reservationDTOList = reservationPage
                .stream()
                .map(this::convertReservationToReservationDTO)
                .toList();
        return new PageImpl<>(reservationDTOList, reservationPage.getPageable(), reservationPage.getTotalElements());
    }
}
