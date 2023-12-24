package com.spring.security.config.jwtservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface JwtService {
    public String extractUserName(String token);
    public String generateToken(String username);
    public boolean isTokenValid(String token, UserDetails userDetails);

//    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}

