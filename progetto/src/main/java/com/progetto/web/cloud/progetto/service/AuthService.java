package com.progetto.web.cloud.progetto.service;

import com.progetto.web.cloud.progetto.dto.AuthDtos.AuthResponse;
import com.progetto.web.cloud.progetto.dto.AuthDtos.LoginRequest;
import com.progetto.web.cloud.progetto.dto.AuthDtos.RegisterRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Map<String, RegisteredUser> users = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        String email = request.email().toLowerCase();
        if (users.containsKey(email)) {
            throw new IllegalArgumentException("Email già registrata");
        }

        String role = request.role() != null ? request.role() : "participant";
        RegisteredUser user = new RegisteredUser(
                email,
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                role
        );
        users.put(email, user);
        return new AuthResponse("Registrazione completata", null, null, email, role);
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.email().toLowerCase();
        RegisteredUser user = users.get(email);
        if (user == null || !passwordEncoder.matches(request.password(), user.passwordHash())) {
            throw new IllegalArgumentException("Credenziali errate");
        }

        String token = generateToken(user);
        return new AuthResponse("Login effettuato", token, "Bearer", user.email(), user.role());
    }

    private String generateToken(RegisteredUser user) {
        return Base64.getEncoder().encodeToString((user.email() + ":" + user.role())
                .getBytes(StandardCharsets.UTF_8));
    }

    private record RegisteredUser(String email, String passwordHash, String firstName, String lastName, String role) {
    }
}
