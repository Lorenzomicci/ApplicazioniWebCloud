package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.ProjectDtos.ProjectRequest;
import com.progetto.web.cloud.progetto.dto.ProjectDtos.ScoreRequest;
import com.progetto.web.cloud.progetto.dto.ProjectDtos.SubmissionRequest;
import io.swagger.v3.oas.annotations.Operation;
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
public class ProjectController {

    @GetMapping("/hackathons/{id}/projects")
    @Operation(summary = "Lista progetti di un hackathon")
    public ResponseEntity<Map<String, String>> listProjects(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco progetti non ancora implementato"));
    }

    @GetMapping("/projects/{projectId}")
    @Operation(summary = "Dettaglio di un progetto")
    public ResponseEntity<Map<String, String>> getProject(@PathVariable String projectId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Dettaglio progetto non ancora implementato"));
    }

    @PostMapping("/teams/{teamId}/projects")
    @Operation(summary = "Crea il progetto di un team")
    public ResponseEntity<Map<String, String>> createProject(@PathVariable String teamId, @RequestBody ProjectRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Creazione progetto non ancora implementata"));
    }

    @PutMapping("/projects/{projectId}")
    @Operation(summary = "Aggiorna un progetto")
    public ResponseEntity<Map<String, String>> updateProject(@PathVariable String projectId, @RequestBody ProjectRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento progetto non ancora implementato"));
    }

    @DeleteMapping("/projects/{projectId}")
    @Operation(summary = "Archivia un progetto")
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable String projectId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Archiviazione progetto non ancora implementata"));
    }

    @PostMapping("/projects/{projectId}/submissions")
    @Operation(summary = "Crea o aggiorna la consegna finale di un progetto")
    public ResponseEntity<Map<String, String>> submit(@PathVariable String projectId, @RequestBody SubmissionRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Consegna non ancora implementata"));
    }

    @GetMapping("/projects/{projectId}/submissions")
    @Operation(summary = "Visualizza la consegna corrente")
    public ResponseEntity<Map<String, String>> getSubmission(@PathVariable String projectId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Recupero consegna non ancora implementato"));
    }

    @GetMapping("/projects/{projectId}/scores")
    @Operation(summary = "Elenco dei punteggi per un progetto")
    public ResponseEntity<Map<String, String>> listScores(@PathVariable String projectId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco punteggi non ancora implementato"));
    }

    @PostMapping("/projects/{projectId}/scores")
    @Operation(summary = "Crea una valutazione per un progetto", description = "Disponibile ai giudici assegnati")
    public ResponseEntity<Map<String, String>> createScore(@PathVariable String projectId, @RequestBody ScoreRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Inserimento punteggio non ancora implementato"));
    }

    @PutMapping("/scores/{scoreId}")
    @Operation(summary = "Aggiorna una valutazione esistente")
    public ResponseEntity<Map<String, String>> updateScore(@PathVariable String scoreId, @RequestBody ScoreRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento punteggio non ancora implementato"));
    }

    @GetMapping("/hackathons/{id}/leaderboard")
    @Operation(summary = "Classifica finale dell'hackathon", description = "Supporta filtri per track")
    public ResponseEntity<Map<String, String>> leaderboard(@PathVariable String id,
                                                           @RequestParam(required = false) String track) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Leaderboard non ancora implementata"));
    }
}
