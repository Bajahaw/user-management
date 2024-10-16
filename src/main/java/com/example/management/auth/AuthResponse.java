package com.example.management.auth;

import com.example.management.AppUser;

public record AuthResponse(AppUser user, String token) {}
