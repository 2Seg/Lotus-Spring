package com.isep.lotus.models;

public class Professeur {

    private String prenom;
    private String nom;
    private String email;
    private boolean enregistre; // pour vérifier si le prof à déjà complété son profil

    // TODO : compléter le mapping Hibernate


    public Professeur() {
    }

    public Professeur(String prenom, String nom, String email, boolean enregistre) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.enregistre = enregistre;
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

    public boolean isEnregistre() {
        return enregistre;
    }

    public void setEnregistre(boolean enregistre) {
        this.enregistre = enregistre;
    }
}
