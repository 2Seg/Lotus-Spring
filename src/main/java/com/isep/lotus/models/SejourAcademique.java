package com.isep.lotus.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "sejour_academique")
public class SejourAcademique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Eleve.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(nullable = false)
    private Eleve eleve;

    @JoinColumn(nullable = false)
    private Integer duree;

    @JoinColumn(nullable = false)
    private Integer semestre;

    @JoinColumn(nullable = false)
    private String pays;

    private String ville;

    private String anneeScolaire;

    private String etablissement;


    public SejourAcademique() {}

    public SejourAcademique(Integer duree, Integer semestre, String pays, String ville, String anneeScolaire, String etablissement) {
        this.duree = duree;
        this.semestre = semestre;
        this.pays = pays;
        this.ville = ville;
        this.anneeScolaire = anneeScolaire;
        this.etablissement = etablissement;
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

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
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

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }
}
