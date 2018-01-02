package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "professeur")
public class Professeur extends Utilisateur {

    @OneToOne(mappedBy = "professeur")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "professeur")
    private List<Message> messages = new ArrayList<>();


    public Professeur() {}

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

}
