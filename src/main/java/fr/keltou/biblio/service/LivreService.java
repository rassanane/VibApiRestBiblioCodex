package fr.keltou.biblio.service;

import fr.keltou.biblio.model.Livre;
import java.util.List;

public interface LivreService {
    Livre create(Livre livre);
    Livre update(Long id, Livre livre);
    void delete(Long id);
    Livre getById(Long id);
    List<Livre> getAll();
}

