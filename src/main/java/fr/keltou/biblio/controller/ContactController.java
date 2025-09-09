package fr.keltou.biblio.controller;

import fr.keltou.biblio.model.Contact;
import fr.keltou.biblio.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private static final Logger log = LoggerFactory.getLogger(ContactController.class);
    private final ContactService service;

    public ContactController(ContactService service) { this.service = service; }

    // Ajoute un contact
    @PostMapping
    public ResponseEntity<Contact> add(@RequestBody Contact c) {
        log.info("POST /api/contacts");
        return ResponseEntity.ok(service.add(c));
    }

    // Liste les contacts
    @GetMapping
    public ResponseEntity<List<Contact>> list() { log.info("GET /api/contacts"); return ResponseEntity.ok(service.list()); }

    // Détail d'un contact
    @GetMapping("/{id}")
    public ResponseEntity<Contact> get(@PathVariable("id") Long id) { log.info("GET /api/contacts/{}", id); return ResponseEntity.ok(service.get(id)); }

    // Supprime un contact
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) { log.info("DELETE /api/contacts/{}", id); service.delete(id); return ResponseEntity.noContent().build(); }
}
