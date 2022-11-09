package com.security.forma_security.Repos;

import com.security.forma_security.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepos extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findAppUserById(Long Id);


//    Boolean existsByUsername(String username);
//
//    Boolean existsByEmail(String email);
}
