package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "parcours")
public class Parcours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "parcours")
    private List<Eleve> eleves = new ArrayList<>();

    @ManyToMany
    private List<Professeur> professeurs = new ArrayList<>();

    @JoinColumn(nullable = false)
    private String nom;


    public Parcours() {}

    public Parcours(int id, List<Eleve> eleves, List<Professeur> professeurs, String nom) {
        this.id = id;
        this.eleves = eleves;
        this.professeurs = professeurs;
        this.nom = nom;
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
}
