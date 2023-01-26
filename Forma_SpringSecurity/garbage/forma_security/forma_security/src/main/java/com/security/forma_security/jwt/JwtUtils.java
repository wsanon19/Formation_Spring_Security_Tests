package com.security.forma_security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.security.forma_security.Model.UserDetailsImpl;
import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.security.forma_security.config.SecurityConstant.AUTHORITIES;
import static java.util.Arrays.stream;

@Component
@Slf4j
public class JwtUtils {

    @Value("${store.app.jwtSecret}")
    private String jwtSecret;

    @Value("${store.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public Map<String, String> generateJwtToken(Authentication authentication) {

        UserDetailsImpl user =  (UserDetailsImpl) authentication.getPrincipal();

        Algorithm algorithm = HMAC256(jwtSecret.getBytes());
        String access_token = JWT.create()
                .withSubject((user.getUsername()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim(AUTHORITIES, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject((user.getUsername()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        return tokens;
    }

    public String getUserNameFromJwtToken(String token) {
        JWTVerifier verifier;
        try {
            verifier = getJWTVerifier();

            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();

            return username;

        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token cannot be verified");
        }

    }

    public boolean isTokenValid(String username, String token) {
        JWTVerifier verifier = getJWTVerifier();
        return !username.isEmpty() && !isTokenExpired(verifier, token);
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    public JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = HMAC256(jwtSecret.getBytes());
            verifier = JWT.require(algorithm).withIssuer("SpringApp").build();
        }catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token cannot be verified");
        }
        return verifier;
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken userPasswordAuthToken = new
                UsernamePasswordAuthenticationToken(username, null, authorities);
        userPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return userPasswordAuthToken;
    }

    public boolean validateJwtToken(String authToken) {
        JWTVerifier verifier;
        try {
            verifier = getJWTVerifier();

            DecodedJWT decodedJWT = verifier.verify(authToken);
            String username = decodedJWT.getSubject();

            return isTokenValid(authToken , username );


        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }


}
