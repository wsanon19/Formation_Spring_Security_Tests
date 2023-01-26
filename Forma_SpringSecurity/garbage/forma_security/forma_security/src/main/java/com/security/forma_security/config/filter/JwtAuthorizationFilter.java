package com.security.forma_security.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.forma_security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils  jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/auth/login")) {
            response.setStatus(OK.value());
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
            }
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                String username = jwtUtils.getSubject(token);

                if(jwtUtils.isTokenValid(username, token)  && SecurityContextHolder.getContext().getAuthentication() == null) {
                    List<GrantedAuthority>  roles = jwtUtils.getAuthorities(token);
                    Authentication authentication = jwtUtils.getAuthentication(username, roles, request);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }else {
                    SecurityContextHolder.clearContext();
                }
            } catch (Exception exception) {
                    log.error("Error logging in : {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(403);
                    //response.sendError(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
        }
        filterChain.doFilter(request, response);
    }
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh")) {
//            filterChain.doFilter(request,response);
//        }else {
//            String authorizationHeader = request.getHeader(AUTHORIZATION);
//            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//                try {
//                    String token = authorizationHeader.substring("Bearer ".length());
//                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                    JWTVerifier verifier = JWT.require(algorithm).build();
//                    DecodedJWT decodedJWT = verifier.verify(token);
//                    String username = decodedJWT.getSubject();
//                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
//                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//                    stream(roles).forEach(role -> {
//                        authorities.add(new SimpleGrantedAuthority(role));
//                    });
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                    filterChain.doFilter(request, response);
//                } catch (Exception exception) {
//                    log.error("Error logging in : {}", exception.getMessage());
//                    response.setHeader("error", exception.getMessage());
//                    response.setStatus(FORBIDDEN.value());
//                    //response.sendError(FORBIDDEN.value());
//                    Map<String, String> error = new HashMap<>();
//                    error.put("error_message", exception.getMessage());
//                    response.setContentType(APPLICATION_JSON_VALUE);
//                    new ObjectMapper().writeValue(response.getOutputStream(), error);
//
//                }
//
//            } else {
//                filterChain.doFilter(request, response);
//            }
//
//
//        }
//    }
}
