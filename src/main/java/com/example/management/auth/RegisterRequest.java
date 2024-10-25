package com.example.management.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "Empty name")
        String name,
        @NotBlank(message = "Empty email")
        @Email(message = "Invalid email")
        String username,
        @NotBlank(message = "Empty password")
        @Size(min = 8, max = 50, message = "Invalid password length")
        String password
) {}
