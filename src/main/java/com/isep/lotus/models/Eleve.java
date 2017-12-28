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
    private String statutScolaire;

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

    @ManyToOne
    private Parcours parcours;

    @ManyToMany(mappedBy = "eleves")
    private List<Cours> cours = new ArrayList<>();


    public Eleve() {}

    public Eleve(int numeroEtudiant, String anneeScolaire, String statutScolaire, String promotion, boolean contact, String linkedin, List<ActiviteExtra> activiteExtras, List<ActivitePro> activitePros, List<Bulletin> bulletins, List<Cv> cvs, List<LettreMotivation> lettreMotivations, List<SejourAcademique> sejourAcademiques, List<Message> messages, Parcours parcours, List<Cours> cours) {
        this.numeroEtudiant = numeroEtudiant;
        this.anneeScolaire = anneeScolaire;
        this.statutScolaire = statutScolaire;
        this.promotion = promotion;
        this.contact = contact;
        this.linkedin = linkedin;
        this.activiteExtras = activiteExtras;
        this.activitePros = activitePros;
        this.bulletins = bulletins;
        this.cvs = cvs;
        this.lettreMotivations = lettreMotivations;
        this.sejourAcademiques = sejourAcademiques;
        this.messages = messages;
        this.parcours = parcours;
        this.cours = cours;
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

    public String getStatutScolaire() {
        return statutScolaire;
    }

    public void setStatutScolaire(String statutScolaire) {
        this.statutScolaire = statutScolaire;
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

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
