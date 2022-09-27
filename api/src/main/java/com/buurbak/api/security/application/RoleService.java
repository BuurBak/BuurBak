package com.buurbak.api.security.application;

import com.buurbak.api.security.data.RoleRepository;
import com.buurbak.api.security.domain.Role;
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
