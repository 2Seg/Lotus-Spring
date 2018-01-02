package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "parcours")
public class Parcours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    private List<Utilisateur> utilisateurs = new ArrayList<>();


    @JoinColumn(nullable = false)
    private String nom;


    public Parcours() {}

    public Parcours(String nom) {
        this.nom = nom;
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

    public void addUtilisateur(Utilisateur utilisateurToAdd) {
        utilisateurs.add(utilisateurToAdd);
        utilisateurToAdd.getParcours().add(this);
    }

    public void removeUtilisateur(Utilisateur utilisateurToRemove) {
        utilisateurs.remove(utilisateurToRemove);
        utilisateurToRemove.getParcours().remove(this);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
