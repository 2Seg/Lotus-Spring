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

    @OneToOne(mappedBy = "image")
    private Utilisateur utilisateur;

    @JoinColumn(nullable = false)
    private String fichier;

    public Image() {}

    public Image(Utilisateur utilisateur, String fichier) {
        this.utilisateur = utilisateur;
        this.fichier = fichier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
}
