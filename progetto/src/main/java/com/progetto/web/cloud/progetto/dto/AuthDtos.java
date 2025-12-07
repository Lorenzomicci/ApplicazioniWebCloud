package com.progetto.web.cloud.progetto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public final class AuthDtos {
    private AuthDtos() {
    }

    @Schema(description = "Dati necessari per la registrazione di un nuovo utente")
    public record RegisterRequest(
            String email,
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
    public record LoginRequest(String email, String password) {
    }
}
