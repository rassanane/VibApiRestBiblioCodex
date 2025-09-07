package fr.keltou.biblio.dto;

import java.time.LocalDate;

public class LivreDto {
    private Long id;
    private String titre;
    private LocalDate datePublication;
    private MaisonEditionDto maisonEdition;

    public LivreDto() {}

    public LivreDto(Long id, String titre, LocalDate datePublication, MaisonEditionDto maisonEdition) {
        this.id = id; this.titre = titre; this.datePublication = datePublication; this.maisonEdition = maisonEdition;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public LocalDate getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDate datePublication) { this.datePublication = datePublication; }
    public MaisonEditionDto getMaisonEdition() { return maisonEdition; }
    public void setMaisonEdition(MaisonEditionDto maisonEdition) { this.maisonEdition = maisonEdition; }
}

