package com.security.forma_security.Service.UserService;

import com.security.forma_security.Model.ERole;
import com.security.forma_security.Model.Role;
import com.security.forma_security.Model.AppUser;
import com.security.forma_security.Repos.UserRepos;

import java.util.List;

public interface UserService  {
    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
//    void addRoleToUser(String username, ERole roleName);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();

}
