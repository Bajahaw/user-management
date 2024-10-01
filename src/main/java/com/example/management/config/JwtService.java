package com.example.management.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String extractUsername(String token) {
        return null;
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        return false;
    }
}
