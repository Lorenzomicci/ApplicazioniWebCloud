package com.progetto.web.cloud.progetto.service;

import com.progetto.web.cloud.progetto.dto.AuthDtos.AuthResponse;
import com.progetto.web.cloud.progetto.dto.AuthDtos.LoginRequest;
import com.progetto.web.cloud.progetto.dto.AuthDtos.RegisterRequest;
import com.progetto.web.cloud.progetto.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        String email = request.email().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email già registrata");
        }

        String role = request.role() != null ? request.role() : "participant";
        userRepository.saveUser(
                email,
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                role
        );
        return new AuthResponse("Registrazione completata", null, null, email, role);
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.email().toLowerCase();
        UserRepository.RegisteredUser user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !passwordEncoder.matches(request.password(), user.passwordHash())) {
            throw new IllegalArgumentException("Credenziali errate");
        }

        String token = generateToken(user);
        return new AuthResponse("Login effettuato", token, "Bearer", user.email(), user.role());
    }

    private String generateToken(UserRepository.RegisteredUser user) {
        return Base64.getEncoder().encodeToString((user.email() + ":" + user.role())
                .getBytes(StandardCharsets.UTF_8));
    }
}
