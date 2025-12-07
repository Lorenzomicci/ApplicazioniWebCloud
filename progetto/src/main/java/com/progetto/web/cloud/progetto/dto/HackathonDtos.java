package com.progetto.web.cloud.progetto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public final class HackathonDtos {
    private HackathonDtos() {
    }

    @Schema(description = "Creazione o aggiornamento di un hackathon")
    public record HackathonRequest(
            @NotBlank(message = "Il nome è obbligatorio")
            String name,
            @NotBlank(message = "Lo slug è obbligatorio")
            String slug,
            String description,
            String location,
            OffsetDateTime registrationStart,
            OffsetDateTime registrationEnd,
            OffsetDateTime eventStart,
            OffsetDateTime eventEnd,
            OffsetDateTime submissionDeadline,
            Integer maxParticipants,
            @Min(value = 1, message = "La dimensione minima del team deve essere almeno 1")
            Integer teamSizeMin,
            @Min(value = 1, message = "La dimensione massima del team deve essere almeno 1")
            Integer teamSizeMax,
            String status
    ) {
    }

    @Schema(description = "Hackathon completo")
    public record HackathonResponse(
            Long id,
            String name,
            String slug,
            String description,
            String location,
            String status,
            OffsetDateTime registrationStart,
            OffsetDateTime registrationEnd,
            OffsetDateTime eventStart,
            OffsetDateTime eventEnd,
            OffsetDateTime submissionDeadline,
            Integer minTeamSize,
            Integer maxTeamSize,
            Integer maxParticipants
    ) {
        public static HackathonResponse fromModel(com.progetto.web.cloud.progetto.model.Hackathon model) {
            return new HackathonResponse(
                    model.id(),
                    model.name(),
                    model.slug(),
                    model.description(),
                    model.location(),
                    model.status(),
                    toOffset(model.registrationStartAt()),
                    toOffset(model.registrationEndAt()),
                    toOffset(model.eventStartAt()),
                    toOffset(model.eventEndAt()),
                    toOffset(model.submissionDeadline()),
                    model.minTeamSize(),
                    model.maxTeamSize(),
                    model.maxParticipants()
            );
        }

        private static OffsetDateTime toOffset(java.time.LocalDateTime localDateTime) {
            return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
        }
    }

    @Schema(description = "Richiesta per creare o aggiornare una track")
    public record TrackRequest(
            @NotBlank(message = "Il nome è obbligatorio")
            String name,
            String description) {
    }

    @Schema(description = "Track associata a un hackathon")
    public record TrackResponse(Long id, Long hackathonId, String name, String description) {
        public static TrackResponse fromModel(com.progetto.web.cloud.progetto.model.HackathonTrack model) {
            return new TrackResponse(model.id(), model.hackathonId(), model.name(), model.description());
        }
    }

    @Schema(description = "Informazioni aggiuntive per l'iscrizione")
    public record RegistrationRequest(String university, String motivation) {
    }

    @Schema(description = "Aggiornamento stato iscrizione")
    public record RegistrationStatusUpdate(String status) {
    }
}
