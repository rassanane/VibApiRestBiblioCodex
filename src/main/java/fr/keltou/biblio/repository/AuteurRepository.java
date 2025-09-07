package fr.keltou.biblio.repository;

import fr.keltou.biblio.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    @Query("select distinct a from Auteur a left join fetch a.livres l left join fetch l.maisonEdition")
    List<Auteur> findAllWithLivres();

    @Query("select a from Auteur a left join fetch a.livres l left join fetch l.maisonEdition where a.id = :id")
    Optional<Auteur> findByIdWithLivres(@Param("id") Long id);
}
