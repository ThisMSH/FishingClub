package com.fishingclub.main.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface IJwtService {
    String extractUsername(String token);
    Date extractExpiration(String token);
    String getToken(UserDetails userDetails);
    String getRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
    Boolean validateToken(String token, UserDetails userDetails);
}
