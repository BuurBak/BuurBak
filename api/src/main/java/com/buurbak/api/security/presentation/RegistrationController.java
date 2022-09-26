package com.buurbak.api.security.presentation;

import com.buurbak.api.security.application.RegistrationService;
import com.buurbak.api.security.presentation.dto.RegistrationRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity register(@Valid @RequestBody RegistrationRequestDTO request) {
        String userId = registrationService.register(request);
        return ResponseEntity.created(URI.create("/api/v1/users/" + userId)).build();
    }

}
