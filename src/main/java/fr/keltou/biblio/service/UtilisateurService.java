package fr.keltou.biblio.service;

import fr.keltou.biblio.model.Utilisateur;
import java.util.List;

public interface UtilisateurService {
    Utilisateur create(Utilisateur u);
    Utilisateur update(Long id, Utilisateur u);
    void delete(Long id);
    Utilisateur getById(Long id);
    List<Utilisateur> getAll();
    boolean authenticate(String identifiant, String motPasse);
    Utilisateur findByCredentials(String identifiant, String motPasse);
}
