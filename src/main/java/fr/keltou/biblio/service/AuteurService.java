package fr.keltou.biblio.service;

import fr.keltou.biblio.model.Auteur;
import java.util.List;

public interface AuteurService {
    Auteur create(Auteur auteur);
    Auteur update(Long id, Auteur auteur);
    void delete(Long id);
    Auteur getById(Long id);
    List<Auteur> getAll();
}

