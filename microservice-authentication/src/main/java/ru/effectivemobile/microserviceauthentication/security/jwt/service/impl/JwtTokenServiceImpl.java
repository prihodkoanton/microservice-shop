package ru.effectivemobile.microserviceauthentication.security.jwt.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.effectivemobile.microserviceauthentication.security.jwt.service.JwtTokenService;

import java.util.Date;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${spring.security.jwt.secret}")
    private String secretKey;

    @Value("${spring.security.jwt.token-validity-milliseconds}")
    private long tokenValidityMilliseconds;

    private final UserDetailsService userDetailsService;

    public JwtTokenServiceImpl(UserDetailsService userDetailsService) {
        this.secretKey = secretKey;
        this.tokenValidityMilliseconds = tokenValidityMilliseconds;
        this.userDetailsService = userDetailsService;
    }

    public String createToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenValidityMilliseconds);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        String username = getUsernameFromToken(token);
        return username.equals(getUserDetailsFromToken(token).getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expireDate = getExpirationDateFromToken(token);
        return expireDate.before(new Date());
    }

    private String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
    }

    private Date getExpirationDateFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getExpiration();
    }

    public UserDetails getUserDetailsFromToken(String token) {
        String username = getUsernameFromToken(token);
        return userDetailsService.loadUserByUsername(username);
    }

    public String refreshToken(String tokenRefreshRequest) {
        UserDetails userDetails = getUserDetailsFromToken(tokenRefreshRequest);
        return createToken(userDetails);
    }
}
