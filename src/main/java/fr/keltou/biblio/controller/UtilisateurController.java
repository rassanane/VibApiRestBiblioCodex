package fr.keltou.biblio.controller;

import fr.keltou.biblio.model.Utilisateur;
import fr.keltou.biblio.service.UtilisateurService;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
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

    // Authentification applicative via formulaire: requiert Basic Auth pour accéder à la route
    // et vérifie dans la base que (identifiant, motPasse) correspondent à un utilisateur
    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> body) {
        String identifiant = body.getOrDefault("identifiant", body.get("username"));
        String motPasse = body.getOrDefault("motPasse", body.get("password"));
        if (identifiant != null) identifiant = identifiant.trim();
        log.info("POST /api/utilisateurs/auth pour {}", identifiant);

        if (identifiant == null || identifiant.isBlank() || motPasse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "identifiant et motPasse sont requis"));
        }
        
        Utilisateur utilisateur = service.findByCredentials(identifiant, motPasse);
        
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        }
                
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Identifiant ou mot de passe incorrect"));
    }
}
