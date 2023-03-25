package ru.effectivemobile.microserviceauthentication.security.jwt;

import lombok.Data;

@Data
public class JwtResponse {

    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
