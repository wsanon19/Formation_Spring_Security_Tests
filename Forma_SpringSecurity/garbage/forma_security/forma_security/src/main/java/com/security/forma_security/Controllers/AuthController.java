package com.security.forma_security.Controllers;

import com.security.forma_security.Model.AppUser;
import com.security.forma_security.Model.ERole;
import com.security.forma_security.Model.Role;
import com.security.forma_security.Model.UserDetailsImpl;
import com.security.forma_security.Repos.RoleRepos;
import com.security.forma_security.Repos.UserRepos;
import com.security.forma_security.Service.UserService.UserService;
import com.security.forma_security.jwt.JwtUtils;
import com.security.forma_security.payloads.LoginRequest;
import com.security.forma_security.payloads.SignupRequest;
import com.security.forma_security.response.JwtResponse;
import com.security.forma_security.response.MessageResponse;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private  AuthenticationManager authenticationManager;
    private  UserRepos userRepository;
    private RoleRepos roleRepository;
    private JwtUtils jwtUtils;

    private PasswordEncoder encoder;

    @Autowired
    public  AuthController(AuthenticationManager authenticationManager, UserRepos userRepos,RoleRepos roleRepos, JwtUtils jwtUtils, PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepos;
        this.roleRepository = roleRepos;
        this.jwtUtils = jwtUtils;
        this.encoder = passwordEncoder;
    }



    @PostMapping("/login")
    public ResponseEntity<AppUser> authenticateUser( @RequestParam("username") String username,
                                                     @RequestParam("password") String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser loginUser = userRepository.findUsersByUsername(username);

        HttpHeaders jwtHeader = getJwtHeader(authentication);
        return new ResponseEntity<>(loginUser,jwtHeader, OK);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        AppUser user = new AppUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Collection<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setAuthorities(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/getjwt/{username}")
    public ResponseEntity<AppUser> login(@PathVariable (value = "username") String username) {

        AppUser loginUser = userRepository.findUsersByUsername(username);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpHeaders jwtHeader = getJwtHeader(authentication);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }


    private HttpHeaders getJwtHeader(Authentication authentication) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Jwt-Token", jwtUtils.generateJwtToken(authentication).get("access_token"));
        headers.add("refresh_token", jwtUtils.generateJwtToken(authentication).get("refresh_token"));
        return headers;
    }
}


