package fr.keltou.biblio.controller;

import fr.keltou.biblio.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UtilisateurService utilisateurService;

    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Vérifie identifiant/mot de passe dans la table utilisateurs
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String identifiant = body.getOrDefault("identifiant", body.get("username"));
        String motPasse = body.getOrDefault("motPasse", body.get("password"));
        log.info("POST /api/auth/login pour {}", identifiant);
        boolean ok = utilisateurService.authenticate(identifiant, motPasse);
        return ResponseEntity.ok(Map.of("authenticated", ok));
    }
}
