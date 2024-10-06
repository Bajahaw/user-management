package com.example.management.auth;

import com.example.management.AppUser;
import com.example.management.UserRepository;
import com.example.management.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepo, PasswordEncoder encoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepo;
        this.passwordEncoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.username(),
                        authRequest.password()
                )
        );
        var user = userRepository.findByUsername(authRequest.username()).orElseThrow();
        var token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest authRequest) {
        var user = AppUser.builder()
                .name(authRequest.name())
                .username(authRequest.username())
                .password(passwordEncoder.encode(authRequest.password()))
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
