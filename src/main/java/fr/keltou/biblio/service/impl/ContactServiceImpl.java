package fr.keltou.biblio.service.impl;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Contact;
import fr.keltou.biblio.repository.ContactRepository;
import fr.keltou.biblio.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);
    private final ContactRepository repository;

    public ContactServiceImpl(ContactRepository repository) { this.repository = repository; }

    @Override public Contact add(Contact c) {
        try { log.info("Ajout contact: {}", c.getEmail()); return repository.save(c);
        } catch (Exception e) { log.error("Erreur ajout contact", e); throw e; }
    }

    @Override public Contact get(Long id) {
        try { return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact introuvable: " + id));
        } catch (Exception e) { log.error("Erreur récupération contact {}", id, e); throw e; }
    }

    @Override public List<Contact> list() {
        try { return repository.findAll();
        } catch (Exception e) { log.error("Erreur listage contacts", e); throw e; }
    }

    @Override public void delete(Long id) {
        try { repository.deleteById(id); log.info("Contact supprimé: {}", id);
        } catch (Exception e) { log.error("Erreur suppression contact {}", id, e); throw e; }
    }
}

