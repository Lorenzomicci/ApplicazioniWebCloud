package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.AuthDtos.LoginRequest;
import com.progetto.web.cloud.progetto.dto.AuthDtos.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    @Operation(summary = "Registra un nuovo utente", description = "Disponibile per participant e mentor")
    @ApiResponse(responseCode = "201", description = "Utente creato")
    @ApiResponse(responseCode = "409", description = "Email già esistente")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Registrazione non ancora implementata"));
    }

    @PostMapping("/login")
    @Operation(summary = "Autentica l'utente", description = "Restituisce un JWT per le chiamate protette")
    @ApiResponse(responseCode = "200", description = "Login riuscito", content = @Content(schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "401", description = "Credenziali errate")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Login non ancora implementato"));
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
    public ResponseEntity<Map<String, String>> me() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Recupero profilo non ancora implementato"));
    }
}
