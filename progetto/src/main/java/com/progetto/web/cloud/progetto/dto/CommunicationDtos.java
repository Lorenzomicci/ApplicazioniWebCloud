package com.progetto.web.cloud.progetto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public final class CommunicationDtos {
    private CommunicationDtos() {
    }

    @Schema(description = "Richiesta per creare o aggiornare un annuncio")
    public record AnnouncementRequest(String title, String content) {
    }

    @Schema(description = "Richiesta per creare o aggiornare una FAQ")
    public record FaqRequest(String question, String answer) {
    }

    @Schema(description = "Associazione manuale progetto-giudice oppure trigger per generazione automatica")
    public record JudgingAssignmentRequest(
            String projectId,
            String judgeId,
            @Schema(description = "Se true, il backend dovrebbe generare le assegnazioni")
            Boolean auto
    ) {
    }
}
