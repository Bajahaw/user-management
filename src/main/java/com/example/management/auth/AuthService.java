package com.example.management.auth;

import com.example.management.exceptions.UsernameIsTaken;
import com.example.management.security.jwt.JwtService;
import com.example.management.user.AppUser;
import com.example.management.user.UserDTO;
import com.example.management.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.warn("Authenticating user: {}", authRequest.username());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.username(),
                            authRequest.password()
                    )
            );
        } catch (AuthenticationException e) {
            log.error("Incorrect username/password supplied");
            throw new BadCredentialsException("Incorrect username/password supplied");
        }

        var user = userRepository.findByUsername(authRequest.username()).orElseThrow();
        var token = jwtService.generateToken(user);
        var userdto = new UserDTO(user.getName(), user.getUsername(), user.getAuthorities());
        log.warn("User authenticated: {}", user.getName());
        return new AuthResponse(userdto, token);
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        userRepository.findByUsername(registerRequest.username()).ifPresent(user -> {
            log.error("Username already exists");
            throw new UsernameIsTaken("Username already exists");
        });
        var user = AppUser.builder()
                .name(registerRequest.name())
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
        Integer userId = userRepository.save(user);
        user.setId(userId);
        var token = jwtService.generateToken(user);
        var userdto = new UserDTO(user.getName(), user.getUsername(), user.getAuthorities());
        log.info("User registered: {}", user.getName());
        return new AuthResponse(userdto, token);
    }

    public void logout(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        assert header != null;
        String token = header.substring(7);
        jwtService.invalidate(token);
    }
}
