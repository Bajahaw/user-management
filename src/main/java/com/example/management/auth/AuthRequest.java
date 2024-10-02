package com.example.management.auth;
import lombok.Builder;

@Builder
public record AuthRequest (String username, String password) {}
