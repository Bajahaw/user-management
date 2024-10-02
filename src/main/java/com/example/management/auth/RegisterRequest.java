package com.example.management.auth;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String username,
        String password
) {}
