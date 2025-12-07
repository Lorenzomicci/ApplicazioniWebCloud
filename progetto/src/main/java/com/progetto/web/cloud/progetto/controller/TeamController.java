package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.TeamDtos.TeamInviteRequest;
import com.progetto.web.cloud.progetto.dto.TeamDtos.TeamRequest;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TeamController {

    @GetMapping("/hackathons/{id}/teams")
    @Operation(summary = "Elenco team di un hackathon")
    public ResponseEntity<Map<String, String>> listTeams(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco team non ancora implementato"));
    }

    @PostMapping("/hackathons/{id}/teams")
    @Operation(summary = "Crea un nuovo team per l'hackathon")
    public ResponseEntity<Map<String, String>> createTeam(@PathVariable String id, @RequestBody TeamRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Creazione team non ancora implementata"));
    }

    @GetMapping("/teams/{teamId}")
    @Operation(summary = "Dettaglio di un team")
    public ResponseEntity<Map<String, String>> getTeam(@PathVariable String teamId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Dettaglio team non ancora implementato"));
    }

    @PutMapping("/teams/{teamId}")
    @Operation(summary = "Aggiorna un team", description = "Disponibile per owner o admin")
    public ResponseEntity<Map<String, String>> updateTeam(@PathVariable String teamId, @RequestBody TeamRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento team non ancora implementato"));
    }

    @DeleteMapping("/teams/{teamId}")
    @Operation(summary = "Scioglie o archivia un team")
    public ResponseEntity<Map<String, String>> deleteTeam(@PathVariable String teamId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Cancellazione team non ancora implementata"));
    }

    @PostMapping("/teams/{teamId}/invites")
    @Operation(summary = "Invita un utente a unirsi al team")
    public ResponseEntity<Map<String, String>> invite(@PathVariable String teamId, @RequestBody TeamInviteRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Invito non ancora implementato"));
    }

    @PostMapping("/team-invites/{inviteId}/accept")
    @Operation(summary = "Accetta un invito")
    public ResponseEntity<Map<String, String>> acceptInvite(@PathVariable String inviteId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Accettazione invito non ancora implementata"));
    }

    @PostMapping("/team-invites/{inviteId}/decline")
    @Operation(summary = "Rifiuta un invito")
    public ResponseEntity<Map<String, String>> declineInvite(@PathVariable String inviteId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Rifiuto invito non ancora implementato"));
    }

    @DeleteMapping("/teams/{teamId}/members/{userId}")
    @Operation(summary = "Rimuove un membro o consente l'uscita dal team")
    public ResponseEntity<Map<String, String>> removeMember(@PathVariable String teamId, @PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Rimozione membro non ancora implementata"));
    }
}
