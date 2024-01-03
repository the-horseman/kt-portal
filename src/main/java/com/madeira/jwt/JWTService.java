package com.madeira.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JWTService {
    
    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public String issueToken(
        String Subject,
        Map<String, Object> claims
    ) {
        String token = Jwts
        .builder()
        .claims(claims)
        .subject(Subject)
        .issuer("https://trellix.kt-portal.com")
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(
            Instant.now().plus(1, ChronoUnit.DAYS)
            ))
        .signWith(SECRET_KEY, Jwts.SIG.HS256)
        .compact();
        return token;
    }
        
    public String issueToken(String subject) {
        return issueToken(subject, Map.of());
    }
    
    public String issueToken(String subject, String ...scopes) {
        return issueToken(subject, Map.of("scopes", scopes));
    }

    public String issueToken(String subject, List<String> scopes) {
        return issueToken(subject, Map.of("scopes", scopes));
    }
    
    public String getSubject(String token) {
        return this.getClaims(token).getSubject();
    }
    
    private Claims getClaims(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        return claims; 
    }

    public boolean isTokenValid(String jwt, String username) {
        String subject = this.getSubject(jwt);
        return subject.equals(username) && !this.isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return this.getClaims(jwt).getExpiration().before(
            Date.from(
                Instant.now()
            )
        );
    }

}
