package fr.keltou.biblio.service;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.MaisonEdition;
import fr.keltou.biblio.repository.MaisonEditionRepository;
import fr.keltou.biblio.service.impl.MaisonEditionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MaisonEditionServiceTest {
    @Test
    void update_throws_when_not_found() {
        MaisonEditionRepository repo = Mockito.mock(MaisonEditionRepository.class);
        Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        MaisonEditionService service = new MaisonEditionServiceImpl(repo);
        assertThrows(ResourceNotFoundException.class, () -> service.update(1L, new MaisonEdition("X")));
    }

    @Test
    void create_returns_saved_entity() {
        MaisonEditionRepository repo = Mockito.mock(MaisonEditionRepository.class);
        MaisonEdition m = new MaisonEdition("Test");
        Mockito.when(repo.save(Mockito.any(MaisonEdition.class))).thenReturn(m);
        MaisonEditionService service = new MaisonEditionServiceImpl(repo);
        assertEquals("Test", service.create(m).getNom());
    }
}

