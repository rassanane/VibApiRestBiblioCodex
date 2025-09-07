package fr.keltou.biblio.service;

import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Contact;
import fr.keltou.biblio.repository.ContactRepository;
import fr.keltou.biblio.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {
    @Test
    void add_and_list_work() {
        ContactRepository repo = Mockito.mock(ContactRepository.class);
        Contact c = new Contact("Nom","email@test","Hello");
        Mockito.when(repo.save(Mockito.any(Contact.class))).thenReturn(c);
        Mockito.when(repo.findAll()).thenReturn(List.of(c));
        ContactService service = new ContactServiceImpl(repo);
        assertEquals("email@test", service.add(c).getEmail());
        assertEquals(1, service.list().size());
    }

    @Test
    void get_throws_when_not_found() {
        ContactRepository repo = Mockito.mock(ContactRepository.class);
        Mockito.when(repo.findById(1L)).thenReturn(Optional.empty());
        ContactService service = new ContactServiceImpl(repo);
        assertThrows(ResourceNotFoundException.class, () -> service.get(1L));
    }
}

