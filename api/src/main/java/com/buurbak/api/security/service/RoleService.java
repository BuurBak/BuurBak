package com.buurbak.api.security.service;

import com.buurbak.api.security.model.Role;
import com.buurbak.api.security.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Role createRole(String roleName) {
        return roleRepository.save(new Role(roleName));
    }
}
