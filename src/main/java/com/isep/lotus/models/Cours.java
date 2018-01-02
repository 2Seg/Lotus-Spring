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

    private String reference;


    public Cours() {}

    public Cours(String nom, String reference) {
        this.nom = nom;
        this.reference = reference;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
