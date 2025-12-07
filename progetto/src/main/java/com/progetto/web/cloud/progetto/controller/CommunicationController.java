package com.progetto.web.cloud.progetto.controller;

import com.progetto.web.cloud.progetto.dto.CommunicationDtos.AnnouncementRequest;
import com.progetto.web.cloud.progetto.dto.CommunicationDtos.FaqRequest;
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
public class CommunicationController {

    @GetMapping("/hackathons/{id}/announcements")
    @Operation(summary = "Elenco annunci di un hackathon")
    public ResponseEntity<Map<String, String>> listAnnouncements(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco annunci non ancora implementato"));
    }

    @PostMapping("/hackathons/{id}/announcements")
    @Operation(summary = "Crea un annuncio", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> createAnnouncement(@PathVariable String id, @RequestBody AnnouncementRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Creazione annuncio non ancora implementata"));
    }

    @PutMapping("/announcements/{announcementId}")
    @Operation(summary = "Aggiorna un annuncio")
    public ResponseEntity<Map<String, String>> updateAnnouncement(@PathVariable String announcementId,
                                                                   @RequestBody AnnouncementRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento annuncio non ancora implementato"));
    }

    @DeleteMapping("/announcements/{announcementId}")
    @Operation(summary = "Archivia o elimina un annuncio")
    public ResponseEntity<Map<String, String>> deleteAnnouncement(@PathVariable String announcementId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Eliminazione annuncio non ancora implementata"));
    }

    @GetMapping("/hackathons/{id}/faq")
    @Operation(summary = "Elenco FAQ di un hackathon")
    public ResponseEntity<Map<String, String>> listFaq(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Elenco FAQ non ancora implementato"));
    }

    @PostMapping("/hackathons/{id}/faq")
    @Operation(summary = "Crea una FAQ", description = "Endpoint admin")
    public ResponseEntity<Map<String, String>> createFaq(@PathVariable String id, @RequestBody FaqRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Creazione FAQ non ancora implementata"));
    }

    @PutMapping("/faq/{faqId}")
    @Operation(summary = "Aggiorna una FAQ")
    public ResponseEntity<Map<String, String>> updateFaq(@PathVariable String faqId, @RequestBody FaqRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Aggiornamento FAQ non ancora implementato"));
    }

    @DeleteMapping("/faq/{faqId}")
    @Operation(summary = "Rimuove una FAQ")
    public ResponseEntity<Map<String, String>> deleteFaq(@PathVariable String faqId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(Map.of("message", "Eliminazione FAQ non ancora implementata"));
    }
}
