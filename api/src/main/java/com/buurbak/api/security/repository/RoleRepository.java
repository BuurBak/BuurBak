package com.buurbak.api.security.repository;

import com.buurbak.api.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
