package fr.keltou.biblio.service.impl;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Auteur;
import fr.keltou.biblio.repository.AuteurRepository;
import fr.keltou.biblio.service.AuteurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurServiceImpl implements AuteurService {
    private static final Logger log = LoggerFactory.getLogger(AuteurServiceImpl.class);
    private final AuteurRepository repository;

    public AuteurServiceImpl(AuteurRepository repository) { this.repository = repository; }

    // Ajoute un auteur
    @Override public Auteur create(Auteur auteur) {
        try { log.info("Création auteur: {} {}", auteur.getPrenom(), auteur.getNom());
            return repository.save(auteur);
        } catch (Exception e) { log.error("Erreur création auteur", e); throw e; }
    }

    // Met à jour un auteur
    @Override public Auteur update(Long id, Auteur auteur) {
        try {
            Auteur existing = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Auteur introuvable: " + id));
            existing.setPrenom(auteur.getPrenom());
            existing.setNom(auteur.getNom());
            return repository.save(existing);
        } catch (Exception e) { log.error("Erreur mise à jour auteur {}", id, e); throw e; }
    }

    // Supprime un auteur
    @Override public void delete(Long id) {
        try { repository.deleteById(id); log.info("Auteur supprimé: {}", id);
        } catch (Exception e) { log.error("Erreur suppression auteur {}", id, e); throw e; }
    }

    // Récupère un auteur par id
    @Override public Auteur getById(Long id) {
        try { return repository.findByIdWithLivres(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auteur introuvable: " + id));
        } catch (Exception e) { log.error("Erreur récupération auteur {}", id, e); throw e; }
    }

    // Liste tous les auteurs
    @Override public List<Auteur> getAll() {
        try { return repository.findAllWithLivres();
        } catch (Exception e) { log.error("Erreur listage auteurs", e); throw e; }
    }
}
