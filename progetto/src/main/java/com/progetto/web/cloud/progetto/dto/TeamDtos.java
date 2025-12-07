package com.progetto.web.cloud.progetto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public final class TeamDtos {
    private TeamDtos() {
    }

    @Schema(description = "Creazione o aggiornamento di un team")
    public record TeamRequest(String name, String description, String trackId) {
    }

    @Schema(description = "Invito a un utente a unirsi al team")
    public record TeamInviteRequest(String userId) {
    }
}
