package com.buurbak.api.security.service;

import com.buurbak.api.security.exception.RoleNotFoundException;
import com.buurbak.api.security.model.Role;
import com.buurbak.api.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException("Role: " + name + " not found"));
    }
}
