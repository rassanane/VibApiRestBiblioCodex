package fr.keltou.biblio.repository;

import fr.keltou.biblio.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {}

