package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.HackathonDtos.RegistrationRequest;
import com.progetto.web.cloud.progetto.dto.HackathonDtos.RegistrationStatusUpdate;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @PostMapping("/hackathons/{id}/registrations")
    @Operation(summary = "Iscrive l'utente loggato a un hackathon")
    public ResponseEntity<Map<String, String>> register(@PathVariable String id, @RequestBody(required = false) RegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Iscrizione non ancora implementata"));
    }

    @GetMapping("/hackathons/{id}/registrations/me")
    @Operation(summary = "Recupera lo stato di iscrizione dell'utente corrente")
    public ResponseEntity<Map<String, String>> myRegistration(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Stato iscrizione non ancora implementato"));
    }

    @GetMapping("/hackathons/{id}/registrations")
    @Operation(summary = "Elenco iscrizioni", description = "Endpoint admin con filtri")
    public ResponseEntity<Map<String, String>> listRegistrations(@PathVariable String id,
                                                                 @RequestParam(required = false) String status,
                                                                 @RequestParam(required = false) String user,
                                                                 @RequestParam(required = false) String team) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco iscrizioni non ancora implementato"));
    }

    @PatchMapping("/registrations/{registrationId}")
    @Operation(summary = "Aggiorna lo stato di una iscrizione", description = "Solo admin")
    public ResponseEntity<Map<String, String>> updateRegistrationStatus(@PathVariable String registrationId,
                                                                         @RequestBody RegistrationStatusUpdate request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento stato iscrizione non ancora implementato"));
    }
}
