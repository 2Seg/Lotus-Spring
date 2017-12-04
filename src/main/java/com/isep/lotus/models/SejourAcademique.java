package com.isep.lotus.models;

import javax.persistence.Entity;

@Entity(name = "sejour_academique")
public class SejourAcademique {

    private int id;

    private Eleve eleve;

    private String duree;

    private String annee; // Changer le type à Date ?

    private String semestre;

    private String pays;

    private String etablissement;


    public SejourAcademique(int id, Eleve eleve, String duree, String annee, String semestre, String pays, String etablissement) {
        this.id = id;
        this.eleve = eleve;
        this.duree = duree;
        this.annee = annee;
        this.semestre = semestre;
        this.pays = pays;
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

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }
}
