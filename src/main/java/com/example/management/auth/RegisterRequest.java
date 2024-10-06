package com.example.management.auth;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String name,
        String username,
        String password
) {}
