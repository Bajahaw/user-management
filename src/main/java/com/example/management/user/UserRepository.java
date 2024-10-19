package com.example.management.user;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<AppUser, Integer> {
    void save(AppUser user);
    Optional<AppUser> findByUsername(String username);
}