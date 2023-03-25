package ru.effectivemobile.microserviceauthentication.security.jwt.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${spring.security.jwt.secret}")
    private String secretKey;

    @Value("${spring.security.jwt.token-validity-milliseconds}")
    private long tokenValidityMilliseconds;

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

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getUserDetailsFromToken(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToke(String token) {
        String username = getUsernameFromToken(token);
        return username != null && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expireDate = getExpirationDateFromToken(token);
        return expireDate != null && expireDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getExpiration();
    }

    private UserDetails getUserDetailsFromToken(String token) {
        String username = getUsernameFromToken(token);
        UserDetails userDetails = null;
        if (username != null) {
            userDetails = new User(username, "", new ArrayList<>());
        }
        return userDetails;
    }

    private String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
    }
}
