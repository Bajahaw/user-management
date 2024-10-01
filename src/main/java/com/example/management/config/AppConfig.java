package com.example.management.config;

import com.example.management.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AppConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.getUserByName(username).orElseThrow()(()-> new RuntimeException("User not found"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return null;
    }
}
