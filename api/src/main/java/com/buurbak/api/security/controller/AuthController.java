package com.buurbak.api.security.controller;

import com.buurbak.api.security.service.AuthService;
import com.buurbak.api.security.service.RegistrationService;
import com.buurbak.api.security.controller.dto.RegistrationRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private RegistrationService registrationService;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequestDTO request) {
        String userId = registrationService.register(request);
        return ResponseEntity.created(URI.create("/api/v1/users/" + userId)).build();
    }

    @GetMapping("refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> tokens = authService.refreshToken(request, response);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
