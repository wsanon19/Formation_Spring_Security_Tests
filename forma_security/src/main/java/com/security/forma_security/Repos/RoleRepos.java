package com.security.forma_security.Repos;

import com.security.forma_security.Model.ERole;
import com.security.forma_security.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepos extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    Role findByName (String name);
}
