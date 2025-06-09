package com.example.test.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // Use Keys.hmacShaKeyFor to ensure proper key length (>= 256 bits for HS256)
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("mySuperSecretKeyThatIsLongEnough12345678901234567890".getBytes());

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();  // username
        } catch (Exception e) {
            return null;  // invalid token
        }
    }
}
