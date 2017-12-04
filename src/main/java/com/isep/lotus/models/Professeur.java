package com.isep.lotus.models;

import javax.persistence.Entity;

@Entity(name = "professeur")
public class Professeur {

    private int id;

    private String prenom;

    private String nom;

    private String email;

    private boolean inscrit; // pour vérifier si le prof à déjà complété son profil



    public Professeur(int id, String prenom, String nom, String email, boolean inscrit) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.inscrit = inscrit;
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

}
