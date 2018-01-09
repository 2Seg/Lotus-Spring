package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "eleve")
public class Eleve extends Utilisateur {

    @JoinColumn(nullable = false)
    private int numeroEtudiant;

    @JoinColumn(nullable = false)
    private String anneeScolaire; // = P1, I2, A3

    @JoinColumn(nullable = false)
    private String activite;

    @JoinColumn(nullable = false)
    private String promotion; // changer le type Date ?

    @JoinColumn(nullable = false)
    private boolean contact = true;

    private String linkedin;

    @OneToOne(mappedBy = "eleve")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "eleve")
    private List<ActiviteExtra> activiteExtras = new ArrayList<>();

    @OneToMany(mappedBy = "eleve")
    private List<ActivitePro> activitePros = new ArrayList<>();

    @OneToMany(mappedBy = "eleve")
    private List<Bulletin> bulletins = new ArrayList<>();

    @OneToMany(mappedBy = "eleve")
    private List<Cv> cvs = new ArrayList<>();

    @OneToMany(mappedBy = "eleve")
    private List<LettreMotivation> lettreMotivations = new ArrayList<>();

    @OneToMany(mappedBy = "eleve")
    private List<SejourAcademique> sejourAcademiques = new ArrayList<>();

    @OneToMany(mappedBy = "eleve")
    private List<Message> messages = new ArrayList<>();


    public Eleve() {}

    public Eleve(int numeroEtudiant, String anneeScolaire, String activite, String promotion, boolean contact, String linkedin) {
        this.numeroEtudiant = numeroEtudiant;
        this.anneeScolaire = anneeScolaire;
        this.activite = activite;
        this.promotion = promotion;
        this.contact = contact;
        this.linkedin = linkedin;
    }

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String statutScolaire) {
        this.activite = statutScolaire;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<ActiviteExtra> getActiviteExtras() {
        return activiteExtras;
    }

    public void setActiviteExtras(List<ActiviteExtra> activiteExtras) {
        this.activiteExtras = activiteExtras;
    }

    public List<ActivitePro> getActivitePros() {
        return activitePros;
    }

    public void setActivitePros(List<ActivitePro> activitePros) {
        this.activitePros = activitePros;
    }

    public List<Bulletin> getBulletins() {
        return bulletins;
    }

    public void setBulletins(List<Bulletin> bulletins) {
        this.bulletins = bulletins;
    }

    public List<Cv> getCvs() {
        return cvs;
    }

    public void setCvs(List<Cv> cvs) {
        this.cvs = cvs;
    }

    public List<LettreMotivation> getLettreMotivations() {
        return lettreMotivations;
    }

    public void setLettreMotivations(List<LettreMotivation> lettreMotivations) {
        this.lettreMotivations = lettreMotivations;
    }

    public List<SejourAcademique> getSejourAcademiques() {
        return sejourAcademiques;
    }

    public void setSejourAcademiques(List<SejourAcademique> sejourAcademiques) {
        this.sejourAcademiques = sejourAcademiques;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
