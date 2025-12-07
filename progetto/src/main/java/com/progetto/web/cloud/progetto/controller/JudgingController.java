package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.CommunicationDtos.JudgingAssignmentRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class JudgingController {

    @GetMapping("/hackathons/{id}/judges")
    @Operation(summary = "Elenco giudici assegnati a un hackathon")
    public ResponseEntity<Map<String, String>> listJudges(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco giudici non ancora implementato"));
    }

    @PostMapping("/hackathons/{id}/judges")
    @Operation(summary = "Aggiunge un giudice", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> addJudge(@PathVariable String id, @RequestBody Map<String, String> request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiunta giudice non ancora implementata"));
    }

    @DeleteMapping("/hackathons/{id}/judges/{userId}")
    @Operation(summary = "Rimuove un giudice", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> removeJudge(@PathVariable String id, @PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Rimozione giudice non ancora implementata"));
    }

    @PostMapping("/hackathons/{id}/judging-assignments")
    @Operation(summary = "Crea o genera assegnazioni progetto-giudice", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> assignJudges(@PathVariable String id,
                                                            @RequestBody(required = false) JudgingAssignmentRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Assegnazione giudici non ancora implementata"));
    }

    @GetMapping("/judges/me/assignments")
    @Operation(summary = "Progetti assegnati al giudice autenticato")
    public ResponseEntity<Map<String, String>> myAssignments(@RequestParam(name = "h", required = false) String hackathonId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Recupero assegnazioni non ancora implementato"));
    }
}
