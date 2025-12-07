package com.progetto.web.cloud.progetto.model;

import java.time.LocalDateTime;

public record Hackathon(
        Long id,
        String name,
        String slug,
        String description,
        String location,
        String status,
        LocalDateTime registrationStartAt,
        LocalDateTime registrationEndAt,
        LocalDateTime eventStartAt,
        LocalDateTime eventEndAt,
        LocalDateTime submissionDeadline,
        Integer minTeamSize,
        Integer maxTeamSize,
        Integer maxParticipants
) {
}
