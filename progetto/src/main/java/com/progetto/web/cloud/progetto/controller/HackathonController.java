package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.HackathonDtos.HackathonRequest;
import com.progetto.web.cloud.progetto.dto.HackathonDtos.TrackRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HackathonController {

    @GetMapping("/hackathons")
    @Operation(summary = "Lista hackathon", description = "Filtri per stato, track e ricerca testuale")
    public ResponseEntity<Map<String, String>> listHackathons(@RequestParam(required = false) String status,
                                                              @RequestParam(required = false) String track,
                                                              @RequestParam(required = false) String search,
                                                              @RequestParam(required = false) Integer page,
                                                              @RequestParam(required = false, name = "pageSize") Integer pageSize,
                                                              @RequestParam(required = false) String sort) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Lista hackathon non ancora implementata"));
    }

    @GetMapping("/hackathons/{id}")
    @Operation(summary = "Dettaglio hackathon")
    public ResponseEntity<Map<String, String>> getHackathon(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Dettaglio hackathon non ancora implementato"));
    }

    @PostMapping("/hackathons")
    @Operation(summary = "Crea un nuovo hackathon", description = "Endpoint admin")
    @ApiResponse(responseCode = "201", description = "Hackathon creato")
    public ResponseEntity<Map<String, String>> createHackathon(@RequestBody HackathonRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Creazione hackathon non ancora implementata"));
    }

    @PutMapping("/hackathons/{id}")
    @Operation(summary = "Aggiorna un hackathon", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> updateHackathon(@PathVariable String id,
                                                               @RequestBody HackathonRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento hackathon non ancora implementato"));
    }

    @DeleteMapping("/hackathons/{id}")
    @Operation(summary = "Archivia un hackathon", description = "Preferibile soft delete impostando lo status")
    public ResponseEntity<Map<String, String>> deleteHackathon(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Archiviazione hackathon non ancora implementata"));
    }

    @GetMapping("/hackathons/{id}/tracks")
    @Operation(summary = "Elenco delle track di un hackathon")
    public ResponseEntity<Map<String, String>> listTracks(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco track non ancora implementato"));
    }

    @PostMapping("/hackathons/{id}/tracks")
    @Operation(summary = "Crea una nuova track", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> createTrack(@PathVariable String id, @RequestBody TrackRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Creazione track non ancora implementata"));
    }

    @PutMapping("/tracks/{trackId}")
    @Operation(summary = "Aggiorna una track", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> updateTrack(@PathVariable String trackId, @RequestBody TrackRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento track non ancora implementato"));
    }

    @DeleteMapping("/tracks/{trackId}")
    @Operation(summary = "Elimina o archivia una track", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> deleteTrack(@PathVariable String trackId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Eliminazione track non ancora implementata"));
    }
}
