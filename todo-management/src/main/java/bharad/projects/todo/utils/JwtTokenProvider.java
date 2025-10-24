package bharad.projects.todo.utils;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
    
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate; 
    
    // Generate JWT Token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        
        String token = Jwts.builder()
                .subject(username) 
                .issuedAt(currentDate) 
                .expiration(expireDate) 
                .signWith(key()) 
                .compact();
        
        return token;
    }
    
    private Key key() {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(jwtSecret)
        );
    }
    
    // Get Username from JWT Token
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key()) 
                .build()
                .parseSignedClaims(token) 
                .getPayload(); 
        
        return claims.getSubject();
    }
    
    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}