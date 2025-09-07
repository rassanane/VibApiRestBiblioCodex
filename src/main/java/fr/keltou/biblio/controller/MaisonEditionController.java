package fr.keltou.biblio.controller;

import fr.keltou.biblio.model.MaisonEdition;
import fr.keltou.biblio.service.MaisonEditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maisons-edition")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MaisonEditionController {
    private static final Logger log = LoggerFactory.getLogger(MaisonEditionController.class);
    private final MaisonEditionService service;

    public MaisonEditionController(MaisonEditionService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<MaisonEdition>> getAll() { log.info("GET /api/maisons-edition"); return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<MaisonEdition> get(@PathVariable("id") Long id) { log.info("GET /api/maisons-edition/{}", id); return ResponseEntity.ok(service.getById(id)); }

    @PostMapping
    public ResponseEntity<MaisonEdition> create(@RequestBody MaisonEdition m) { log.info("POST /api/maisons-edition"); return ResponseEntity.ok(service.create(m)); }

    @PutMapping("/{id}")
    public ResponseEntity<MaisonEdition> update(@PathVariable("id") Long id, @RequestBody MaisonEdition m) { log.info("PUT /api/maisons-edition/{}", id); return ResponseEntity.ok(service.update(id, m)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) { log.info("DELETE /api/maisons-edition/{}", id); service.delete(id); return ResponseEntity.noContent().build(); }
}
