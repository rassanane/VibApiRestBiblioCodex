package fr.keltou.biblio.service.impl;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Livre;
import fr.keltou.biblio.repository.LivreRepository;
import fr.keltou.biblio.service.LivreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImpl implements LivreService {
    private static final Logger log = LoggerFactory.getLogger(LivreServiceImpl.class);
    private final LivreRepository repository;

    public LivreServiceImpl(LivreRepository repository) { this.repository = repository; }

    @Override public Livre create(Livre livre) {
        try { log.info("Création livre: {}", livre.getTitre()); return repository.save(livre);
        } catch (Exception e) { log.error("Erreur création livre", e); throw e; }
    }

    @Override public Livre update(Long id, Livre livre) {
        try {
            Livre existing = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Livre introuvable: " + id));
            existing.setTitre(livre.getTitre());
            existing.setDatePublication(livre.getDatePublication());
            existing.setMaisonEdition(livre.getMaisonEdition());
            existing.setAuteurs(livre.getAuteurs());
            return repository.save(existing);
        } catch (Exception e) { log.error("Erreur mise à jour livre {}", id, e); throw e; }
    }

    @Override public void delete(Long id) {
        try { repository.deleteById(id); log.info("Livre supprimé: {}", id);
        } catch (Exception e) { log.error("Erreur suppression livre {}", id, e); throw e; }
    }

    @Override public Livre getById(Long id) {
        try { return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre introuvable: " + id));
        } catch (Exception e) { log.error("Erreur récupération livre {}", id, e); throw e; }
    }

    @Override public List<Livre> getAll() {
        try { return repository.findAll();
        } catch (Exception e) { log.error("Erreur listage livres", e); throw e; }
    }
}

