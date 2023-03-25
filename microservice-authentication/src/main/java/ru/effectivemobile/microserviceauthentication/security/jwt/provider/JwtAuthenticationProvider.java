package ru.effectivemobile.microserviceauthentication.security.jwt.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.effectivemobile.microserviceauthentication.security.jwt.JwtAuthenticationToken;
import ru.effectivemobile.microserviceauthentication.security.jwt.service.impl.JwtTokenServiceImpl;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenServiceImpl jwtTokenServiceImpl;

    public JwtAuthenticationProvider(JwtTokenServiceImpl jwtTokenServiceImpl) {
        this.jwtTokenServiceImpl = jwtTokenServiceImpl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        String jwtToken = token.getToken();

        if (jwtToken == null) {
            throw new IllegalAccessError("No JWT token found in request headers");
        }
        if (!jwtTokenServiceImpl.validateToken(jwtToken)) {
            throw new IllegalAccessError("JWT token is not valid");
        }

        UserDetails userDetails = jwtTokenServiceImpl.getUserDetailsFromToken(jwtToken);

        return new JwtAuthenticationToken(userDetails, jwtToken, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
