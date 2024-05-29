package net.backendApplication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key secretKey;

    public JwtUtil() {
        // Generate a secure key with size >= 256 bits
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String username, Long userId, String firstName, String lastName) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("firstName", firstName)
                .claim("lastName", lastName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours of validity
                .signWith(secretKey)
                .compact();
    }
}
