package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.AuthDtos.AuthResponse;
import com.progetto.web.cloud.progetto.dto.AuthDtos.LoginRequest;
import com.progetto.web.cloud.progetto.dto.AuthDtos.RegisterRequest;
import com.progetto.web.cloud.progetto.service.AuthService;
import com.progetto.web.cloud.progetto.user.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserProfileService userProfileService;

    public AuthController(AuthService authService, UserProfileService userProfileService) {
        this.authService = authService;
        this.userProfileService = userProfileService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registra un nuovo utente", description = "Disponibile per participant e mentor")
    @ApiResponse(responseCode = "201", description = "Utente creato")
    @ApiResponse(responseCode = "409", description = "Email già esistente")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthResponse(ex.getMessage(), null, null, request.email(), request.role()));
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Autentica l'utente", description = "Restituisce un JWT per le chiamate protette")
    @ApiResponse(responseCode = "200", description = "Login riuscito", content = @Content(schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "401", description = "Credenziali errate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(ex.getMessage(), null, null, request.email(), null));
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Invalida il token lato server se presente una denylist")
    @ApiResponse(responseCode = "204", description = "Logout registrato")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/me")
    @Operation(summary = "Profilo utente", description = "Restituisce il profilo dell'utente autenticato")
    @ApiResponse(responseCode = "200", description = "Profilo recuperato")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Utente non autenticato"));
        }
        return ResponseEntity.ok(userProfileService.buildUserProfile(authentication));
    }
}
