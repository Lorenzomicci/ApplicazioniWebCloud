package com.progetto.web.cloud.progetto.service;

import com.progetto.web.cloud.progetto.dto.HackathonDtos.HackathonRequest;
import com.progetto.web.cloud.progetto.dto.HackathonDtos.HackathonResponse;
import com.progetto.web.cloud.progetto.dto.HackathonDtos.TrackRequest;
import com.progetto.web.cloud.progetto.dto.HackathonDtos.TrackResponse;
import com.progetto.web.cloud.progetto.model.Hackathon;
import com.progetto.web.cloud.progetto.model.HackathonTrack;
import com.progetto.web.cloud.progetto.repository.HackathonRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HackathonService {

    private final HackathonRepository repository;

    public HackathonService(HackathonRepository repository) {
        this.repository = repository;
    }

    public List<HackathonResponse> list(String status, String search) {
        return repository.findAll(status, search)
                .stream()
                .map(HackathonResponse::fromModel)
                .toList();
    }

    public Optional<HackathonResponse> findById(Long id) {
        return repository.findById(id).map(HackathonResponse::fromModel);
    }

    @Transactional
    public HackathonResponse create(HackathonRequest request) {
        Hackathon hackathon = new Hackathon(
                null,
                request.name(),
                request.slug(),
                request.description(),
                request.location(),
                request.status() != null ? request.status() : "DRAFT",
                toLocalDateTime(request.registrationStart()),
                toLocalDateTime(request.registrationEnd()),
                toLocalDateTime(request.eventStart()),
                toLocalDateTime(request.eventEnd()),
                toLocalDateTime(request.submissionDeadline()),
                request.teamSizeMin() != null ? request.teamSizeMin() : 1,
                request.teamSizeMax() != null ? request.teamSizeMax() : 5,
                request.maxParticipants()
        );

        return HackathonResponse.fromModel(repository.save(hackathon));
    }

    @Transactional
    public Optional<HackathonResponse> update(Long id, HackathonRequest request) {
        Hackathon hackathon = new Hackathon(
                id,
                request.name(),
                request.slug(),
                request.description(),
                request.location(),
                request.status() != null ? request.status() : "DRAFT",
                toLocalDateTime(request.registrationStart()),
                toLocalDateTime(request.registrationEnd()),
                toLocalDateTime(request.eventStart()),
                toLocalDateTime(request.eventEnd()),
                toLocalDateTime(request.submissionDeadline()),
                request.teamSizeMin() != null ? request.teamSizeMin() : 1,
                request.teamSizeMax() != null ? request.teamSizeMax() : 5,
                request.maxParticipants()
        );

        int updated = repository.update(id, hackathon);
        return updated > 0 ? Optional.of(HackathonResponse.fromModel(hackathon)) : Optional.empty();
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id) > 0;
    }

    public List<TrackResponse> listTracks(Long hackathonId) {
        return repository.findTracks(hackathonId).stream().map(TrackResponse::fromModel).toList();
    }

    @Transactional
    public TrackResponse createTrack(Long hackathonId, TrackRequest request) {
        if (repository.findById(hackathonId).isEmpty()) {
            throw new IllegalArgumentException("Hackathon not found");
        }
        HackathonTrack track = new HackathonTrack(null, hackathonId, request.name(), request.description(), null, null);
        return TrackResponse.fromModel(repository.insertTrack(hackathonId, track));
    }

    @Transactional
    public boolean updateTrack(Long trackId, TrackRequest request) {
        HackathonTrack track = new HackathonTrack(trackId, null, request.name(), request.description(), null, null);
        return repository.updateTrack(trackId, track) > 0;
    }

    @Transactional
    public boolean deleteTrack(Long trackId) {
        return repository.deleteTrack(trackId) > 0;
    }

    private LocalDateTime toLocalDateTime(java.time.OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }
}
