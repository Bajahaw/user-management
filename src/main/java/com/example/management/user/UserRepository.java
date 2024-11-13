package com.example.management.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static AppUser rowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new AppUser(
                rs.getInt("ID"),
                rs.getString("NAME"),
                rs.getString("USERNAME"),
                rs.getString("PASSWORD"),
                Arrays
                        .stream(rs.getString("ROLES").split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList()
                );
    }

    public void save(AppUser user) {
        String sql = "INSERT INTO USER_TABLE (NAME, USERNAME, PASSWORD, ROLES) VALUES (?, ?, ?, ?)";
        StringBuilder roles = new StringBuilder();
        user.getAuthorities().forEach(sga -> {
            roles.append(sga.getAuthority());
            roles.append(',');
        });
        jdbcTemplate.update(
                sql, user.getName(),
                user.getUsername(),
                user.getPassword(),
                roles
        );
    }

    public Optional<AppUser> findByUsername(String username) {
        String sql = "SELECT * FROM USER_TABLE WHERE USERNAME = ?";
        return jdbcTemplate.query(
                sql,
                UserRepository::rowMapper,
                username
        ).stream().findFirst();
    }

    public List<AppUser> getAllUsers() {
        String sql = "SELECT * FROM USER_TABLE";
        return jdbcTemplate.query(sql, UserRepository::rowMapper);
    }

    public boolean delete(String username) {
        String sql = "DELETE FROM USER_TABLE WHERE USERNAME = ?";
        int succeeded = jdbcTemplate.update(sql, username);
        return succeeded == 1;
    }
}
