package com.isep.lotus.models;

import javax.persistence.*;

/**
 * Created by IntelliJ.
 * User:  eliott
 * Date: 05/12/17
 * Time: 21:45
 */
@Entity(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "image", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Eleve eleve;

    @OneToOne(mappedBy = "image", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Professeur professeur;

    @JoinColumn(nullable = false)
    private String fichier;



    public Image() {}

    public Image(int id, Eleve eleve, Professeur professeur, String fichier) {
        this.id = id;
        this.eleve = eleve;
        this.professeur = professeur;
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

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
}
