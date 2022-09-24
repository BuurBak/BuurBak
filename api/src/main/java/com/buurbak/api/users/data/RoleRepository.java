package com.buurbak.api.users.data;

import com.buurbak.api.users.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
