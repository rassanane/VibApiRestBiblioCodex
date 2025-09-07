package fr.keltou.biblio.service;

import fr.keltou.biblio.model.Contact;
import java.util.List;

public interface ContactService {
    Contact add(Contact c);
    Contact get(Long id);
    List<Contact> list();
    void delete(Long id);
}

