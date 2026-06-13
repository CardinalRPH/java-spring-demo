package com.example.demojava.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {
    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    private final long JWT_EXPIRATION = 60;

    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
    }

    public <T> T exctractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimResolver.apply(claims);
    }

    public String extractUsername(String token){
        return exctractClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired  (String token) {
        return exctractClaim(token, Claims::getExpiration).before(new Date());
    }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }




}
