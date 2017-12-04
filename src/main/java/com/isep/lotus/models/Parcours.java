package com.isep.lotus.models;

import javax.persistence.Entity;

@Entity(name = "parcours")
public class Parcours {

    private int id;

    private Eleve eleve;

    private Professeur professeur;

    private ListeParcours nom;


    public Parcours(int id, Eleve eleve, Professeur professeur, ListeParcours nom) {
        this.id = id;
        this.eleve = eleve;
        this.professeur = professeur;
        this.nom = nom;
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

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public ListeParcours getNom() {
        return nom;
    }

    public void setNom(ListeParcours nom) {
        this.nom = nom;
    }
}
