package com.example.management;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public AppUser findByUsername(String username) {
        String sql = "SELECT * FROM USER_TABLE WHERE USERNAME = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, UserRepository::rowMapper);
    }
}
