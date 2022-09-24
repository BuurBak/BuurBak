package com.buurbak.api.users.application;

import com.buurbak.api.users.data.RoleRepository;
import com.buurbak.api.users.domain.Role;
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
