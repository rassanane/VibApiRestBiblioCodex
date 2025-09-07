package fr.keltou.biblio.service;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Livre;
import fr.keltou.biblio.repository.LivreRepository;
import fr.keltou.biblio.service.impl.LivreServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LivreServiceTest {
    @Test
    void create_and_list_work() {
        LivreRepository repo = Mockito.mock(LivreRepository.class);
        Livre l = new Livre("Titre", LocalDate.now());
        Mockito.when(repo.save(Mockito.any(Livre.class))).thenReturn(l);
        Mockito.when(repo.findAll()).thenReturn(List.of(l));

        LivreService service = new LivreServiceImpl(repo);
        assertEquals("Titre", service.create(l).getTitre());
        assertEquals(1, service.getAll().size());
    }

    @Test
    void update_throws_when_not_found() {
        LivreRepository repo = Mockito.mock(LivreRepository.class);
        Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        LivreService service = new LivreServiceImpl(repo);
        assertThrows(ResourceNotFoundException.class, () -> service.update(1L, new Livre()));
    }

    @Test
    void getById_throws_when_not_found() {
        LivreRepository repo = Mockito.mock(LivreRepository.class);
        Mockito.when(repo.findById(99L)).thenReturn(Optional.empty());
        LivreService service = new LivreServiceImpl(repo);
        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }
}

