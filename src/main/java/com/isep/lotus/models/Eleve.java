package com.isep.lotus.models;

public class Eleve {

    private String prenom;
    private String nom;
    private String email;
    private boolean enregistre;
    private int numero_etudiant;
    private String annee_scolaire; // = P1, I2, A3
    private String statut_scolaire;
    private String promotion; // changer le type à DateTime ou Date ?


    // TODO : compléter le mapping Hibernate

    public Eleve() {
    }

    public Eleve(String prenom, String nom, String email, boolean enregistre, int numero_etudiant, String annee_scolaire, String statut_scolaire, String promotion) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.enregistre = enregistre;
        this.numero_etudiant = numero_etudiant;
        this.annee_scolaire = annee_scolaire;
        this.statut_scolaire = statut_scolaire;
        this.promotion = promotion;
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

    public int getNumero_etudiant() {
        return numero_etudiant;
    }

    public void setNumero_etudiant(int numero_etudiant) {
        this.numero_etudiant = numero_etudiant;
    }

    public String getAnnee_scolaire() {
        return annee_scolaire;
    }

    public void setAnnee_scolaire(String annee_scolaire) {
        this.annee_scolaire = annee_scolaire;
    }

    public String getStatut_scolaire() {
        return statut_scolaire;
    }

    public void setStatut_scolaire(String statut_scolaire) {
        this.statut_scolaire = statut_scolaire;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
}
