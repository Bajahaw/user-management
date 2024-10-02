package com.example.management;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<String, AppUser> users = new HashMap<>();

    public void save(AppUser user) {
        users.put(user.getUsername() , user);
    }

    public AppUser getUserByName(String username) {
        return users.get(username);
    }
}
