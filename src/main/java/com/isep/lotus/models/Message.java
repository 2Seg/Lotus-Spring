package com.isep.lotus.models;

import javax.persistence.*;

@Entity(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Eleve eleve;

    @ManyToOne
    private Professeur professeur;

    @JoinColumn(nullable = false)
    private int destinataire_id;

    @JoinColumn(nullable = false)
    private String message;


    public Message() {}

    public Message(Eleve eleve, Professeur professeur, int destinataire_id, String message) {
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
