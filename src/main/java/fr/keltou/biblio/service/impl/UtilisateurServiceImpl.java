package fr.keltou.biblio.service.impl;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Utilisateur;
import fr.keltou.biblio.repository.UtilisateurRepository;
import fr.keltou.biblio.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private static final Logger log = LoggerFactory.getLogger(UtilisateurServiceImpl.class);
    private final UtilisateurRepository repository;

    public UtilisateurServiceImpl(UtilisateurRepository repository) { this.repository = repository; }

    @Override public Utilisateur create(Utilisateur u) {
        try { log.info("Création utilisateur: {}", u.getIdentifiant()); return repository.save(u);
        } catch (Exception e) { log.error("Erreur création utilisateur", e); throw e; }
    }

    @Override public Utilisateur update(Long id, Utilisateur u) {
        try {
            Utilisateur existing = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable: " + id));
            existing.setNom(u.getNom());
            existing.setPrenom(u.getPrenom());
            existing.setIdentifiant(u.getIdentifiant());
            existing.setMotPasse(u.getMotPasse());
            return repository.save(existing);
        } catch (Exception e) { log.error("Erreur mise à jour utilisateur {}", id, e); throw e; }
    }

    @Override public void delete(Long id) {
        try { repository.deleteById(id); log.info("Utilisateur supprimé: {}", id);
        } catch (Exception e) { log.error("Erreur suppression utilisateur {}", id, e); throw e; }
    }

    @Override public Utilisateur getById(Long id) {
        try { return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable: " + id));
        } catch (Exception e) { log.error("Erreur récupération utilisateur {}", id, e); throw e; }
    }

    @Override public List<Utilisateur> getAll() {
        try { return repository.findAll();
        } catch (Exception e) { log.error("Erreur listage utilisateurs", e); throw e; }
    }

    @Override public boolean authenticate(String identifiant, String motPasse) {
        try {
            boolean found = repository.findByIdentifiant(identifiant).isPresent();
            boolean ok = repository.findByIdentifiant(identifiant)
                    .map(u -> u.getMotPasse() != null && u.getMotPasse().equals(motPasse))
                    .orElse(false);
            log.info("Authentification utilisateur identifiant='{}' found={} ok={}", identifiant, found, ok);
            return ok;
        } catch (Exception e) { log.error("Erreur authentification pour {}", identifiant, e); throw e; }
    }

    @Override
    public Utilisateur findByCredentials(String identifiant, String motPasse) {
        try {
            return repository.findByIdentifiant(identifiant)
                    .filter(u -> u.getMotPasse() != null && u.getMotPasse().equals(motPasse))
                    .orElse(null);
        } catch (Exception e) { log.error("Erreur recherche utilisateur par credentials pour {}", identifiant, e); throw e; }
    }
}
