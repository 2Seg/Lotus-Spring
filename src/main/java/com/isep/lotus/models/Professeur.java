package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "professeur")
public class Professeur {

    @Id
    @GeneratedValue
    private int id;

    private String prenom;

    private String nom;

    private String email;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Image image;

    @OneToMany(mappedBy = "professeur")
    private List<Message> messages = new ArrayList<>();

    @ManyToMany(mappedBy = "professeurs")
    private List<Parcours> parcours = new ArrayList<>();

    @ManyToMany(mappedBy = "professeurs")
    private List<Cours> cours = new ArrayList<>();


    public Professeur() {}

    public Professeur(int id, String prenom, String nom, String email, Image image, List<Message> messages, List<Parcours> parcours, List<Cours> cours) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.image = image;
        this.messages = messages;
        this.parcours = parcours;
        this.cours = cours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
