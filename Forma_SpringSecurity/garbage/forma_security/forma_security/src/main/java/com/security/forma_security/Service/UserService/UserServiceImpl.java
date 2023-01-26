package com.security.forma_security.Service.UserService;

import com.security.forma_security.Model.ERole;
import com.security.forma_security.Model.Role;
import com.security.forma_security.Model.AppUser;
import com.security.forma_security.Repos.RoleRepos;
import com.security.forma_security.Repos.UserRepos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl  implements UserService {

    private final UserRepos userRepos;
    private final RoleRepos roleRepos;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveUser(AppUser appUser) {

        log.info("Saving new user {} to the database", appUser.getName());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepos.save(appUser);    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getName());
        return roleRepos.save(role);
    }

    @Override
        public void addRoleToUser(String username, String roleName) {
        log.info("Saving role {} to the user {}",roleName,username);
        AppUser appUser = userRepos.findUsersByUsername(username);
        Role role = roleRepos.findByName(ERole.valueOf(roleName)).orElseThrow(() -> new RuntimeException("Error: Role is not found."));;
        appUser.getAuthorities().add(role);

    }

//    @Override
//    public void addRoleToUser(String username, String roleName) {
//        AppUser appUser = userRepos.findUsersByUsername(username);
//        Role role = roleRepos.findByName(roleName);
//        appUser.getRoles().add(role);
//    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}",username);
        return userRepos.findUsersByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepos.findAll();
    }

}
