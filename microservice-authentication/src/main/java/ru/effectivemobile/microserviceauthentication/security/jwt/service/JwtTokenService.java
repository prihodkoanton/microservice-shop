package ru.effectivemobile.microserviceauthentication.security.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;


public interface JwtTokenService {
    String createToken(UserDetails userDetails);

    boolean validateToken(String token);

    UserDetails getUserDetailsFromToken(String token);

    String refreshToken(String tokenRefreshRequest);
}
