package fr.keltou.biblio.service.impl;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.MaisonEdition;
import fr.keltou.biblio.repository.MaisonEditionRepository;
import fr.keltou.biblio.service.MaisonEditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaisonEditionServiceImpl implements MaisonEditionService {
    private static final Logger log = LoggerFactory.getLogger(MaisonEditionServiceImpl.class);
    private final MaisonEditionRepository repository;

    public MaisonEditionServiceImpl(MaisonEditionRepository repository) { this.repository = repository; }

    @Override public MaisonEdition create(MaisonEdition m) {
        try { log.info("Création maison d'édition: {}", m.getNom()); return repository.save(m);
        } catch (Exception e) { log.error("Erreur création maison d'édition", e); throw e; }
    }

    @Override public MaisonEdition update(Long id, MaisonEdition m) {
        try {
            MaisonEdition existing = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Maison d'édition introuvable: " + id));
            existing.setNom(m.getNom());
            return repository.save(existing);
        } catch (Exception e) { log.error("Erreur mise à jour maison d'édition {}", id, e); throw e; }
    }

    @Override public void delete(Long id) {
        try { repository.deleteById(id); log.info("Maison d'édition supprimée: {}", id);
        } catch (Exception e) { log.error("Erreur suppression maison d'édition {}", id, e); throw e; }
    }

    @Override public MaisonEdition getById(Long id) {
        try { return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maison d'édition introuvable: " + id));
        } catch (Exception e) { log.error("Erreur récupération maison d'édition {}", id, e); throw e; }
    }

    @Override public List<MaisonEdition> getAll() {
        try { return repository.findAll();
        } catch (Exception e) { log.error("Erreur listage maisons d'édition", e); throw e; }
    }
}

