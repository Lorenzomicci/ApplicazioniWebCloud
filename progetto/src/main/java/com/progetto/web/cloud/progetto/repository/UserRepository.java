package com.progetto.web.cloud.progetto.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<RegisteredUser> mapper = (rs, rowNum) -> new RegisteredUser(
            rs.getLong("id"),
            rs.getString("email"),
            rs.getString("password_hash"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("role")
    );

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        initializeSchema();
    }

    private void initializeSchema() {
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    email VARCHAR(255) NOT NULL UNIQUE,
                    password_hash VARCHAR(255) NOT NULL,
                    first_name VARCHAR(255),
                    last_name VARCHAR(255),
                    role VARCHAR(64) NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """);
    }

    public boolean existsByEmail(String email) {
        Boolean exists = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) > 0 FROM users WHERE email = ?",
                Boolean.class,
                email
        );
        return Boolean.TRUE.equals(exists);
    }

    public Optional<RegisteredUser> findByEmail(String email) {
        List<RegisteredUser> users = jdbcTemplate.query(
                "SELECT id, email, password_hash, first_name, last_name, role FROM users WHERE email = ?",
                mapper,
                email
        );
        return users.stream().findFirst();
    }

    public long saveUser(String email, String passwordHash, String firstName, String lastName, String role) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users (email, password_hash, first_name, last_name, role) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, email);
            ps.setString(2, passwordHash);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, role);
            return ps;
        }, keyHolder);
        Number id = null;
        if (keyHolder.getKeys() != null) {
            Object key = keyHolder.getKeys().get("id");
            if (key instanceof Number number) {
                id = number;
            }
        }
        return id != null ? id.longValue() : -1L;
    }

    public record RegisteredUser(Long id, String email, String passwordHash, String firstName, String lastName, String role) {
    }
}
