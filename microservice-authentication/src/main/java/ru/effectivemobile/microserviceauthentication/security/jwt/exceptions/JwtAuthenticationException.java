package ru.effectivemobile.microserviceauthentication.security.jwt.exceptions;

public class JwtAuthenticationException extends RuntimeException{

    public JwtAuthenticationException(String message, Exception e) {
        super(message, e);
    }
}
