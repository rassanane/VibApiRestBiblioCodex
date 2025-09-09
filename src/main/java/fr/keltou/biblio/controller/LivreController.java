package fr.keltou.biblio.controller;

import fr.keltou.biblio.model.Livre;
import fr.keltou.biblio.service.LivreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {
    private static final Logger log = LoggerFactory.getLogger(LivreController.class);
    private final LivreService service;

    public LivreController(LivreService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Livre>> getAll() { log.info("GET /api/livres"); return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> get(@PathVariable("id") Long id) { log.info("GET /api/livres/{}", id); return ResponseEntity.ok(service.getById(id)); }

    @PostMapping
    public ResponseEntity<Livre> create(@RequestBody Livre l) { log.info("POST /api/livres"); return ResponseEntity.ok(service.create(l)); }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> update(@PathVariable("id") Long id, @RequestBody Livre l) { log.info("PUT /api/livres/{}", id); return ResponseEntity.ok(service.update(id, l)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) { log.info("DELETE /api/livres/{}", id); service.delete(id); return ResponseEntity.noContent().build(); }
}
