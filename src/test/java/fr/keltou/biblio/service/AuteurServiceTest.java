package fr.keltou.biblio.service;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Auteur;
import fr.keltou.biblio.repository.AuteurRepository;
import fr.keltou.biblio.service.impl.AuteurServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuteurServiceTest {
    @Test
    void update_throws_when_not_found() {
        AuteurRepository repo = Mockito.mock(AuteurRepository.class);
        Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        AuteurService service = new AuteurServiceImpl(repo);
        assertThrows(ResourceNotFoundException.class, () -> service.update(1L, new Auteur("A","B")));
    }

    @Test
    void create_returns_saved_entity() {
        AuteurRepository repo = Mockito.mock(AuteurRepository.class);
        Auteur a = new Auteur("A","B");
        Mockito.when(repo.save(Mockito.any(Auteur.class))).thenReturn(a);
        AuteurService service = new AuteurServiceImpl(repo);
        assertEquals("A", service.create(a).getPrenom());
    }
}

