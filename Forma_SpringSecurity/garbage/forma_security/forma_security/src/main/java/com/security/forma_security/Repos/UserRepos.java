package com.security.forma_security.Repos;

import com.security.forma_security.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepos extends JpaRepository<AppUser, Long> {
    AppUser findUsersByUsername(String username);

    Optional<AppUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
