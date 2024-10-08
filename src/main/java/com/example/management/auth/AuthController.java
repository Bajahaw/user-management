package com.example.management.auth;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<AuthResponse> authenticate(@ModelAttribute AuthRequest authRequest) {
        return ResponseEntity.ok(new AuthResponse(authService.authenticate(authRequest)));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest authRequest) {
        return ResponseEntity.ok(authService.register(authRequest));
    }

}
