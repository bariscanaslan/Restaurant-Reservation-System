package com.example.rrsystem.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "ec9fa18aaa6665c8f7926c1ae2f54e51d728d4192e428df4eddf31f8f0584cb4c389119f2765f27fbb03cb7fe8bb34030c28e57213822fb8bef55a9ad87a05b3f9e6c62b1ecfb5dbd7521e9beab7a7fbbf65c33d682fcb10be91e32b80ad17ca32e745e23d7bdbecedfea746b35d27d9d080f7d1a5d61c7a927f90d2d9fc3c39";
    private static final long EXPIRATION_TIME = 86400000;

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username, int userType, int customerId, Long userId) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .claim("userType", userType)
                .claim("customerId", customerId)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256);

        return builder.compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        Object userIdObject = getClaims(token).get("userId");

        if (userIdObject instanceof Integer) {
            return ((Integer) userIdObject).longValue();
        } else if (userIdObject instanceof Long) {
            return (Long) userIdObject;
        } else {
            throw new IllegalArgumentException("userId is neither Integer nor Long");
        }
    }

    public Long extractCustomerId(String token) {
        Object customerIdObject = getClaims(token).get("customerId");

        if (customerIdObject instanceof Integer) {
            return ((Integer) customerIdObject).longValue();
        }
        else if (customerIdObject instanceof Long) {
            return (Long) customerIdObject;
        }
        else {
            throw new IllegalArgumentException("customerId is neither Integer nor Long");
        }
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}