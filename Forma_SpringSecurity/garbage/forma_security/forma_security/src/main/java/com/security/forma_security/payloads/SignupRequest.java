package com.security.forma_security.payloads;

import com.security.forma_security.Model.Role;

import java.util.Collection;

public class SignupRequest {
    private String username;

    private String password;


    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Collection<String> role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Collection<String> getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Collection<String> role) {
        this.role = role;
    }
}
