package com.buurbak.api.security.presentation;

import com.buurbak.api.security.application.UserService;
import com.buurbak.api.security.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;

    @GetMapping("self")
    public User getSelf(Authentication authentication) {
        return userService.findByUsername(authentication.getName());
    }
}
