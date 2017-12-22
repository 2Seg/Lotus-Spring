package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "professeur")
public class Professeur extends Utilisateur {

    @OneToOne(mappedBy = "eleve")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "professeur")
    private List<Message> messages = new ArrayList<>();

    @ManyToMany(mappedBy = "professeurs")
    private List<Parcours> parcours = new ArrayList<>();

    @ManyToMany(mappedBy = "professeurs")
    private List<Cours> cours = new ArrayList<>();


    public Professeur() {}

    public Professeur(List<Message> messages, List<Parcours> parcours, List<Cours> cours) {
        this.messages = messages;
        this.parcours = parcours;
        this.cours = cours;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(List<Parcours> parcours) {
        this.parcours = parcours;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
