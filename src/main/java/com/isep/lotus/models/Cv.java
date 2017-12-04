package com.isep.lotus.models;

import javax.persistence.Entity;

@Entity(name = "cv")
public class Cv {

    private int id;

    private Eleve eleve;

    private String fichier;



    public Cv(int id, Eleve eleve, String fichier) {
        this.id = id;
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
