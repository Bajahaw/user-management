package com.example.management.app;

import com.example.management.auth.AuthService;
import com.example.management.user.AppUser;
import com.example.management.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AppUser> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public boolean deleteUser(String user) {
        return userRepository.delete(user);
    }
}
