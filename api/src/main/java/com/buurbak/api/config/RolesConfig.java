package com.buurbak.api.config;

import com.buurbak.api.security.model.Role;
import com.buurbak.api.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RolesConfig implements CommandLineRunner {
    @Autowired
    private RoleService roleService;

    private final String[] ROLES = new String[] { "USER", "ADMIN", "MODERATOR" };

    public void run(String... args) {
        for (String role : ROLES) {
            if (!roleService.existsByName(role)) {
                roleService.saveRole(
                        new Role(role)
                );
            }
        }
    }
}
