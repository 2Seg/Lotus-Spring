package com.isep.lotus.models;

import javax.persistence.*;

@Entity(name = "activite")
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable = false)
    private String nom;

    public Activite() {
    }

    public Activite(String nom) {
        this.nom = nom;
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
}
