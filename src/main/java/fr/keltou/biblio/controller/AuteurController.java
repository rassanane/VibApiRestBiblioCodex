package fr.keltou.biblio.controller;

import fr.keltou.biblio.dto.AuteurDto;
import fr.keltou.biblio.dto.LivreCardDto;
import fr.keltou.biblio.model.Auteur;
import fr.keltou.biblio.model.Livre;
import fr.keltou.biblio.model.MaisonEdition;
import fr.keltou.biblio.service.AuteurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {
    private static final Logger log = LoggerFactory.getLogger(AuteurController.class);
    private final AuteurService service;

    public AuteurController(AuteurService service) { this.service = service; }

    // Liste tous les auteurs
    @GetMapping
    public ResponseEntity<List<AuteurDto>> getAll() {
        log.info("GET /api/auteurs");
        List<Auteur> auteurs = service.getAll();
        return ResponseEntity.ok(auteurs.stream().map(this::toDto).toList());
    }

    // Récupère un auteur
    @GetMapping("/{id}")
    public ResponseEntity<AuteurDto> get(@PathVariable("id") Long id) {
        log.info("GET /api/auteurs/{}", id);
        return ResponseEntity.ok(toDto(service.getById(id)));
    }

    // Crée un auteur
    @PostMapping
    public ResponseEntity<Auteur> create(@RequestBody Auteur a) {
        log.info("POST /api/auteurs");
        return ResponseEntity.ok(service.create(a));
    }

    // Met à jour un auteur
    @PutMapping("/{id}")
    public ResponseEntity<Auteur> update(@PathVariable("id") Long id, @RequestBody Auteur a) {
        log.info("PUT /api/auteurs/{}", id);
        return ResponseEntity.ok(service.update(id, a));
    }

    // Supprime un auteur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("DELETE /api/auteurs/{}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    private AuteurDto toDto(Auteur a) {
        AuteurDto dto = new AuteurDto(a.getId(), a.getPrenom(), a.getNom());
        for (Livre l : a.getLivres()) {
            String maisonNom = l.getMaisonEdition() != null ? l.getMaisonEdition().getNom() : null;
            dto.getSesLivres().add(new LivreCardDto(l.getId(), l.getTitre(), l.getDatePublication(), maisonNom));
        }
        return dto;
    }
}
