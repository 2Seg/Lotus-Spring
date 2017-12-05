package com.isep.lotus.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "eleve")
public class Eleve {

    @Id
    private int id;

    private String prenom;

    private String nom;

    private String email;

    private boolean inscrit;

    private int numeroEtudiant;

    private String anneeScolaire; // = P1, I2, A3

    private String statutScolaire;

    private String promotion; // changer le type Date ?

    public Eleve() {
    }

    public Eleve(int id, String prenom, String nom, String email, boolean inscrit, int numeroEtudiant, String anneeScolaire, String statutScolaire, String promotion) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.inscrit = inscrit;
        this.numeroEtudiant = numeroEtudiant;
        this.anneeScolaire = anneeScolaire;
        this.statutScolaire = statutScolaire;
        this.promotion = promotion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isInscrit() {
        return inscrit;
    }

    public void setInscrit(boolean inscrit) {
        this.inscrit = inscrit;
    }

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getStatutScolaire() {
        return statutScolaire;
    }

    public void setStatutScolaire(String statutScolaire) {
        this.statutScolaire = statutScolaire;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

}
