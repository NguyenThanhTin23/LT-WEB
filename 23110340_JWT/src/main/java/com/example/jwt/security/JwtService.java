package com.example.jwt.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secret;
    @Value("${security.jwt.expiration-time}")
    private long expiration;
    public String generate(UserDetails user){
        Date now=new Date();
        Date exp=new Date(now.getTime()+expiration);
        return Jwts.builder().subject(user.getUsername()).issuedAt(now).expiration(exp).signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).compact();
    }
    public String extractUsername(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public boolean isValid(String token,UserDetails user){
        String u=extractUsername(token);
        Date exp=Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token).getPayload().getExpiration();
        return u.equals(user.getUsername()) && exp.after(new Date());
    }
}
