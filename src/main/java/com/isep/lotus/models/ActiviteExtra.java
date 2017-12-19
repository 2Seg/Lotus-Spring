package com.isep.lotus.models;

import javax.persistence.*;

@Entity(name = "activite_extra_scolaire")
public class ActiviteExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(nullable = false)
    private Eleve eleve;

    @JoinColumn(nullable = false)
    private String nom;



    public ActiviteExtra() {}

    public ActiviteExtra(Eleve eleve, String nom) {
        this.eleve = eleve;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
