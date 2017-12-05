package com.isep.lotus.models;

import javax.persistence.Entity;

@Entity(name = "cours")
public class Cours {

    private int id;

    private String nom;

    private String reference;


    public Cours(int id, String nom, String reference) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
