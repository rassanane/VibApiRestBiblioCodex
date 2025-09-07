package fr.keltou.biblio.service;

import fr.keltou.biblio.model.MaisonEdition;
import java.util.List;

public interface MaisonEditionService {
    MaisonEdition create(MaisonEdition m);
    MaisonEdition update(Long id, MaisonEdition m);
    void delete(Long id);
    MaisonEdition getById(Long id);
    List<MaisonEdition> getAll();
}

