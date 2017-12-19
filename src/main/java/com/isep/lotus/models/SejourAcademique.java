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
    private String duree;

    private Date datedebut;

    private Date dateFin;

    @JoinColumn(nullable = false)
    private String semestre;

    @JoinColumn(nullable = false)
    private String pays;

    private String etablissement;


    public SejourAcademique() {}

    public SejourAcademique(Eleve eleve, String duree, Date datedebut, Date dateFin, String semestre, String pays, String etablissement) {
        this.eleve = eleve;
        this.duree = duree;
        this.datedebut = datedebut;
        this.dateFin = dateFin;
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

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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
