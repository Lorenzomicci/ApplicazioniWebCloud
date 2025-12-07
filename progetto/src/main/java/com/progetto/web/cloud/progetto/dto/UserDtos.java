package com.progetto.web.cloud.progetto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public final class UserDtos {
    private UserDtos() {
    }

    @Schema(description = "Aggiornamento profilo pubblico")
    public record UpdateUserProfileRequest(String bio, String skills, String links) {
    }

    @Schema(description = "Richiesta di cambio ruolo")
    public record UpdateUserRoleRequest(String role) {
    }
}
