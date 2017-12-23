package com.isep.lotus.models;

import javax.persistence.*;

@Entity(name = "cv")
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Eleve.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(nullable = false)
    private Eleve eleve;

    @JoinColumn(nullable = false)
    private String fichier;


    public Cv() {}

    public Cv(Eleve eleve, String fichier) {
        this.eleve = eleve;
        this.fichier = fichier;
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

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
}
