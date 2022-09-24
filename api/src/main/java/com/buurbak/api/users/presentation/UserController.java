package com.buurbak.api.users.presentation;

import com.buurbak.api.users.application.CustomerService;
import com.buurbak.api.users.application.UserService;
import com.buurbak.api.users.domain.Customer;
import com.buurbak.api.users.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "users")
public class UserController {
    private final CustomerService customerService;
}
