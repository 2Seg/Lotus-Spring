package com.isep.lotus.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "message")
public class Message {

    @Id
    private int id;

    @ManyToOne
    private Eleve eleve;

    @ManyToOne
    private Professeur professeur;

    private int destinataire_id;

    private String message;


    public Message() {}

    public Message(int id, Eleve eleve, Professeur professeur, int destinataire_id, String message) {
        this.id = id;
        this.eleve = eleve;
        this.professeur = professeur;
        this.destinataire_id = destinataire_id;
        this.message = message;
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

    public int getDestinataire_id() {
        return destinataire_id;
    }

    public void setDestinataire_id(int destinataire_id) {
        this.destinataire_id = destinataire_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
