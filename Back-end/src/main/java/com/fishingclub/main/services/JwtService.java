package com.fishingclub.main.services;

import com.fishingclub.main.services.interfaces.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtService implements IJwtService {
    @Value("${jwt.secret}")
    private static String secret;

    public String extractUsername(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    public String getToken(UserDetails userDetails) {
        return this.generateToken(userDetails.getUsername());
    }

    @Override
    public String getRefreshToken(Map<String, Object> map, UserDetails userDetails) {
        return this.generateRefreshToken(map, userDetails.getUsername());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(this.getSignKey())
                .compact();
    }

    private String generateRefreshToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                .signWith(this.getSignKey())
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(this.getSignKey())
                .build()
                .parseUnsecuredClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        byte[] key = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }
}
