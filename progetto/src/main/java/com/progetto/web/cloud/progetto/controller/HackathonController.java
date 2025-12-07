package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.HackathonDtos.HackathonRequest;
import com.progetto.web.cloud.progetto.dto.HackathonDtos.HackathonResponse;
import com.progetto.web.cloud.progetto.dto.HackathonDtos.TrackRequest;
import com.progetto.web.cloud.progetto.dto.HackathonDtos.TrackResponse;
import com.progetto.web.cloud.progetto.service.HackathonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HackathonController {

    private final HackathonService hackathonService;

    public HackathonController(HackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    @GetMapping("/hackathons")
    @Operation(summary = "Lista hackathon", description = "Filtri per stato, track e ricerca testuale")
    public ResponseEntity<List<HackathonResponse>> listHackathons(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(hackathonService.list(status, search));
    }

    @GetMapping("/hackathons/{id}")
    @Operation(summary = "Dettaglio hackathon")
    public ResponseEntity<HackathonResponse> getHackathon(@PathVariable Long id) {
        Optional<HackathonResponse> hackathon = hackathonService.findById(id);
        return hackathon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/hackathons")
    @Operation(summary = "Crea un nuovo hackathon", description = "Endpoint admin")
    @ApiResponse(responseCode = "201", description = "Hackathon creato")
    public ResponseEntity<HackathonResponse> createHackathon(@Valid @RequestBody HackathonRequest request) {
        HackathonResponse created = hackathonService.create(request);
        return ResponseEntity.created(URI.create("/api/hackathons/" + created.id())).body(created);
    }

    @PutMapping("/hackathons/{id}")
    @Operation(summary = "Aggiorna un hackathon", description = "Endpoint admin")
    public ResponseEntity<HackathonResponse> updateHackathon(@PathVariable Long id,
                                                             @Valid @RequestBody HackathonRequest request) {
        Optional<HackathonResponse> updated = hackathonService.update(id, request);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/hackathons/{id}")
    @Operation(summary = "Archivia un hackathon", description = "Preferibile soft delete impostando lo status")
    public ResponseEntity<Void> deleteHackathon(@PathVariable Long id) {
        boolean deleted = hackathonService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/hackathons/{id}/tracks")
    @Operation(summary = "Elenco delle track di un hackathon")
    public ResponseEntity<List<TrackResponse>> listTracks(@PathVariable Long id) {
        return ResponseEntity.ok(hackathonService.listTracks(id));
    }

    @PostMapping("/hackathons/{id}/tracks")
    @Operation(summary = "Crea una nuova track", description = "Endpoint admin")
    public ResponseEntity<TrackResponse> createTrack(@PathVariable Long id, @Valid @RequestBody TrackRequest request) {
        try {
            TrackResponse track = hackathonService.createTrack(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(track);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/tracks/{trackId}")
    @Operation(summary = "Aggiorna una track", description = "Endpoint admin")
    public ResponseEntity<Void> updateTrack(@PathVariable Long trackId, @Valid @RequestBody TrackRequest request) {
        boolean updated = hackathonService.updateTrack(trackId, request);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/tracks/{trackId}")
    @Operation(summary = "Elimina o archivia una track", description = "Endpoint admin")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long trackId) {
        boolean deleted = hackathonService.deleteTrack(trackId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
