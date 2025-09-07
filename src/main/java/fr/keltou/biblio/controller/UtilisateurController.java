package fr.keltou.biblio.controller;

import fr.keltou.biblio.model.Utilisateur;
import fr.keltou.biblio.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UtilisateurController {
    private static final Logger log = LoggerFactory.getLogger(UtilisateurController.class);
    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAll() { log.info("GET /api/utilisateurs"); return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> get(@PathVariable("id") Long id) { log.info("GET /api/utilisateurs/{}", id); return ResponseEntity.ok(service.getById(id)); }

    @PostMapping
    public ResponseEntity<Utilisateur> create(@RequestBody Utilisateur u) { log.info("POST /api/utilisateurs"); return ResponseEntity.ok(service.create(u)); }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable("id") Long id, @RequestBody Utilisateur u) { log.info("PUT /api/utilisateurs/{}", id); return ResponseEntity.ok(service.update(id, u)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) { log.info("DELETE /api/utilisateurs/{}", id); service.delete(id); return ResponseEntity.noContent().build(); }
}
