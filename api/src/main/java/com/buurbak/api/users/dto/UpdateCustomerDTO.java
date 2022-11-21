package com.buurbak.api.users.dto;

import com.buurbak.api.security.dto.CreateAddressDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerDTO {

//    @NotBlank @Email
//    private String email;
//    @NotBlank
//    private String password;
    @NotBlank
    private String name;
//    @NotBlank
//    private String number;
//    @Valid
//    private CreateAddressDTO address;
//    @Past @JsonProperty("date_of_birth")
//    private LocalDate dateOfBirth;
//    @NotBlank
//    private String iban;
}
