package fr.keltou.biblio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AuteurDto {
    private Long id;
    private String prenom;
    private String nom;
    private String nomComplet;

    @JsonProperty("sesLivres")
    private List<LivreCardDto> sesLivres = new ArrayList<>();

    public AuteurDto() {}

    public AuteurDto(Long id, String prenom, String nom) {
        this.id = id; this.prenom = prenom; this.nom = nom; this.nomComplet = buildNomComplet(prenom, nom);
    }

    private static String buildNomComplet(String prenom, String nom) {
        String p = prenom != null ? prenom : "";
        String n = nom != null ? nom : "";
        String space = (!p.isBlank() && !n.isBlank()) ? " " : "";
        return p + space + n;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; this.nomComplet = buildNomComplet(this.prenom, this.nom); }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; this.nomComplet = buildNomComplet(this.prenom, this.nom); }
    public String getNomComplet() { return nomComplet; }
    public void setNomComplet(String nomComplet) { this.nomComplet = nomComplet; }
    public List<LivreCardDto> getSesLivres() { return sesLivres; }
    public void setSesLivres(List<LivreCardDto> sesLivres) { this.sesLivres = sesLivres; }
}
