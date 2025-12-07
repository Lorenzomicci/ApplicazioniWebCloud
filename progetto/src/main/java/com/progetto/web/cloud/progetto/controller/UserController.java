package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.UserDtos.UpdateUserProfileRequest;
import com.progetto.web.cloud.progetto.dto.UserDtos.UpdateUserRoleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    @Operation(summary = "Elenco utenti", description = "Visibile agli admin con filtri per ruolo e testo libero")
    public ResponseEntity<Map<String, String>> listUsers(@RequestParam(required = false) String role,
                                                         @RequestParam(required = false) String search) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco utenti non ancora implementato"));
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Profilo pubblico di un utente")
    public ResponseEntity<Map<String, String>> getUser(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Profilo utente non ancora implementato"));
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Aggiorna il profilo dell'utente", description = "Permesso al proprietario o admin")
    @ApiResponse(responseCode = "200", description = "Profilo aggiornato")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable String id,
                                                          @RequestBody UpdateUserProfileRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento profilo non ancora implementato"));
    }

    @PatchMapping("/users/{id}/role")
    @Operation(summary = "Aggiorna il ruolo dell'utente", description = "Solo admin")
    public ResponseEntity<Map<String, String>> updateRole(@PathVariable String id,
                                                          @RequestBody UpdateUserRoleRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento ruolo non ancora implementato"));
    }
}
