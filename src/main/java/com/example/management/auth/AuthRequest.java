package com.example.management.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AuthRequest (
        @Email(message = "Invalid email")
        @NotBlank(message = "Empty email")
        String username,

        @NotBlank(message = "Empty Password")
        @Size(min = 8, max = 50, message = "Invalid password length")
        String password
) {}
