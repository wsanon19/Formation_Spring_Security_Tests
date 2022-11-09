package com.security.forma_security.config.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;

public class UserDTO {

    private String username;
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.roles = new ArrayList<>();
        user.getAuthorities().forEach(role -> {
            roles.add(role.getAuthority());
        });
    }


}
