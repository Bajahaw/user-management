package com.example.management;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends Repository<AppUser, Integer> {
    void save(AppUser user);
    Optional<AppUser> findByUsername(String username);
}