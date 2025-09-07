package fr.keltou.biblio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "livres")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    @Column(name = "date_publication")
    private LocalDate datePublication;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_maison_edition")
    private MaisonEdition maisonEdition;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "livres_auteurs",
            joinColumns = @JoinColumn(name = "id_livre"),
            inverseJoinColumns = @JoinColumn(name = "id_auteur"))
    private Set<Auteur> auteurs = new HashSet<>();

    public Livre() {}

    public Livre(String titre, LocalDate datePublication) {
        this.titre = titre;
        this.datePublication = datePublication;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public LocalDate getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDate datePublication) { this.datePublication = datePublication; }
    public MaisonEdition getMaisonEdition() { return maisonEdition; }
    public void setMaisonEdition(MaisonEdition maisonEdition) { this.maisonEdition = maisonEdition; }
    public Set<Auteur> getAuteurs() { return auteurs; }
    public void setAuteurs(Set<Auteur> auteurs) { this.auteurs = auteurs; }
}
