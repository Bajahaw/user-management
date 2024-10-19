package com.example.management.auth;

import com.example.management.user.AppUser;

public record AuthResponse(AppUser user, String token) {}
