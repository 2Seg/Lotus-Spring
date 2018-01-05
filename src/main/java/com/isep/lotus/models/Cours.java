package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    private String nom;


    public Cours() {}

    public Cours(String nom) {
        this.nom = nom;
    }

    public void addUtilisateur(Utilisateur utilisateurToAdd) {
        utilisateurs.add(utilisateurToAdd);
        utilisateurToAdd.getCours().add(this);
    }

    public void removeUtilisateur(Utilisateur utilisateurToRemove) {
        utilisateurs.remove(utilisateurToRemove);
        utilisateurToRemove.getCours().remove(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
