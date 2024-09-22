package com.example.management;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AppRepository {
    private final Map<String, AppUser> users = new HashMap<>();

    public void save(AppUser user) {
        users.put(user.username(), user);
    }

    public AppUser getUserByName(String username) {
        return users.get(username);
    }
}
