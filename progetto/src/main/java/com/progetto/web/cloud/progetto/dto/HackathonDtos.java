package com.progetto.web.cloud.progetto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

public final class HackathonDtos {
    private HackathonDtos() {
    }

    @Schema(description = "Creazione o aggiornamento di un hackathon")
    public record HackathonRequest(
            String name,
            String description,
            String theme,
            OffsetDateTime registrationStart,
            OffsetDateTime registrationEnd,
            OffsetDateTime eventStart,
            OffsetDateTime eventEnd,
            OffsetDateTime submissionDeadline,
            Integer maxParticipants,
            Integer teamSizeMin,
            Integer teamSizeMax,
            String status
    ) {
    }

    @Schema(description = "Richiesta per creare o aggiornare una track")
    public record TrackRequest(String name, String description) {
    }

    @Schema(description = "Informazioni aggiuntive per l'iscrizione")
    public record RegistrationRequest(String university, String motivation) {
    }

    @Schema(description = "Aggiornamento stato iscrizione")
    public record RegistrationStatusUpdate(String status) {
    }
}
