package com.isep.lotus.models;

import javax.persistence.*;

@Entity(name = "activite_extra_scolaire")
public class ActiviteExtra {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "eleve_id", foreignKey = @ForeignKey(name = "ELEVE_ACTIVITE_EXTRA_FK"))
    private Eleve eleve;

    private String nom;

    public ActiviteExtra(int id, Eleve eleve, String nom) {
        this.id = id;
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
