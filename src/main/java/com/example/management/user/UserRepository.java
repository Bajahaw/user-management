package com.example.management.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                rs.getString("PASSWORD")
        );
    }

    public void save(AppUser user) {
        String sql = "INSERT INTO USER_TABLE (NAME, USERNAME, PASSWORD) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword());
    }

    public Optional<AppUser> findByUsername(String username) {
        String sql = "SELECT * FROM USER_TABLE WHERE USERNAME = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        sql,
                        UserRepository::rowMapper,
                        username
                )
        );
    }
}
