package fr.keltou.biblio.dto;

import java.time.LocalDate;

public class LivreCardDto {
    private Long id;
    private String titre;
    private LocalDate datePublication;
    private String maisonEditionNom;

    public LivreCardDto() {}

    public LivreCardDto(Long id, String titre, LocalDate datePublication, String maisonEditionNom) {
        this.id = id; this.titre = titre; this.datePublication = datePublication; this.maisonEditionNom = maisonEditionNom;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public LocalDate getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDate datePublication) { this.datePublication = datePublication; }
    public String getMaisonEditionNom() { return maisonEditionNom; }
    public void setMaisonEditionNom(String maisonEditionNom) { this.maisonEditionNom = maisonEditionNom; }
}

