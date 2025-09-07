package fr.keltou.biblio.service;

import fr.keltou.biblio.model.Utilisateur;
import fr.keltou.biblio.repository.UtilisateurRepository;
import fr.keltou.biblio.service.impl.UtilisateurServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

public class UtilisateurServiceTest {
    @Test
    void authenticate_returns_true_when_credentials_match() {
        UtilisateurRepository repo = Mockito.mock(UtilisateurRepository.class);
        Utilisateur u = new Utilisateur("Doe", "John", "john", "pwd");
        Mockito.when(repo.findByIdentifiant(anyString())).thenReturn(Optional.of(u));

        UtilisateurService service = new UtilisateurServiceImpl(repo);
        assertTrue(service.authenticate("john", "pwd"));
        assertFalse(service.authenticate("john", "wrong"));
    }
}

