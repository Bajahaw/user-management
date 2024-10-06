package com.example.management;

import org.springframework.stereotype.Service;

@Service
public class AppService {
    private final UserRepository userRepository;

    public AppService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public void addUser(AppUser user) {
        userRepository.save(user);
    }

}
