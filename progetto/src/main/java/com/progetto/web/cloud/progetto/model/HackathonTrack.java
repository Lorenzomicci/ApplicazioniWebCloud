package com.progetto.web.cloud.progetto.model;

import java.time.LocalDateTime;

public record HackathonTrack(
        Long id,
        Long hackathonId,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
