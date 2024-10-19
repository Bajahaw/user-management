package com.example.management.app;

import com.example.management.user.AppUser;
import com.example.management.user.UserRepository;
import com.example.management.auth.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    private final UserRepository userRepository;
    private final AuthService authService;

    public AppService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public AppUser getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public void addUser(AppUser user) {
        userRepository.save(user);
    }
}
