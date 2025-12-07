package com.progetto.web.cloud.progetto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public final class AuthDtos {
    private AuthDtos() {
    }

    @Schema(description = "Dati necessari per la registrazione di un nuovo utente")
    public record RegisterRequest(
            @Email(message = "Email non valida")
            @NotBlank(message = "L'email è obbligatoria")
            String email,
            @NotBlank(message = "La password è obbligatoria")
            String password,
            @Schema(description = "Nome")
            String firstName,
            @Schema(description = "Cognome")
            String lastName,
            @Schema(description = "Ruolo richiesto: participant o mentor")
            String role
    ) {
    }

    @Schema(description = "Credenziali per l'autenticazione")
    public record LoginRequest(
            @Email(message = "Email non valida")
            @NotBlank(message = "L'email è obbligatoria")
            String email,
            @NotBlank(message = "La password è obbligatoria")
            String password) {
    }

    public record AuthResponse(String message, String token, String tokenType, String email, String role) {
    }
}
