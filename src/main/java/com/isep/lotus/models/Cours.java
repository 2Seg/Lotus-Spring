package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    private List<Eleve> eleves = new ArrayList<>();

    @ManyToMany
    private List<Professeur> professeurs = new ArrayList<>();

    private String nom;

    private String reference;


    public Cours() {}

    public Cours(List<Eleve> eleves, List<Professeur> professeurs, String nom, String reference) {
        this.eleves = eleves;
        this.professeurs = professeurs;
        this.nom = nom;
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public List<Professeur> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(List<Professeur> professeurs) {
        this.professeurs = professeurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
