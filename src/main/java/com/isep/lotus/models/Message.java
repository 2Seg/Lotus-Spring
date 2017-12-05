package com.isep.lotus.models;

public class Message {

    private int id;

    private Eleve eleve;

    private Professeur professeur;

    private int idDestinataire;

    private String message;


    public Message(int id, Eleve eleve, Professeur professeur, int idDestinataire, String message) {
        this.id = id;
        this.eleve = eleve;
        this.professeur = professeur;
        this.idDestinataire = idDestinataire;
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

    public int getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(int idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
