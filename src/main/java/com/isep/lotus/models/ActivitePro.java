package com.isep.lotus.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "activite_professionnelle")
public class ActivitePro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(nullable = false)
    private Eleve eleve;

    private String typeContrat;

    private Integer duree;

    private String nomEntreprise;

    private String poste;

    private String description;

    private String pays;

    private String ville;


    public ActivitePro() {}

    public ActivitePro(String typeContrat, Integer duree, String nomEntreprise, String poste, String description, String pays, String ville) {
        this.typeContrat = typeContrat;
        this.duree = duree;
        this.nomEntreprise = nomEntreprise;
        this.poste = poste;
        this.description = description;
        this.pays = pays;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
