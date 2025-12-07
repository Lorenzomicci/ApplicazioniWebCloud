package com.progetto.web.cloud.progetto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public final class ProjectDtos {
    private ProjectDtos() {
    }

    @Schema(description = "Creazione o aggiornamento di un progetto")
    public record ProjectRequest(
            String title,
            String description,
            String repoUrl,
            String demoUrl,
            String pitchDeckUrl
    ) {
    }

    @Schema(description = "Consegna finale di un progetto")
    public record SubmissionRequest(String artifactUrl, String notes) {
    }

    @Schema(description = "Valutazione di un progetto da parte di un giudice")
    public record ScoreRequest(
            Integer innovation,
            Integer technicalQuality,
            Integer impact,
            Integer presentation,
            String comment
    ) {
    }
}
