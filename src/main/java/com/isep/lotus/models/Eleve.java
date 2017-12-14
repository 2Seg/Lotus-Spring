package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "eleve")
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable = false)
    private String prenom;

    @JoinColumn(nullable = false)
    private String nom;

    @JoinColumn(nullable = false)
    private String email;

    @JoinColumn(nullable = false)
    private int numeroEtudiant;

    @JoinColumn(nullable = false)
    private String anneeScolaire; // = P1, I2, A3

    @JoinColumn(nullable = false)
    private String statutScolaire;

    @JoinColumn(nullable = false)
    private String promotion; // changer le type Date ?

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Image image;

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

    public Eleve(int id, String prenom, String nom, String email, int numeroEtudiant, String anneeScolaire, String statutScolaire, String promotion, Image image, List<ActiviteExtra> activiteExtras, List<ActivitePro> activitePros, List<Bulletin> bulletins, List<Cv> cvs, List<LettreMotivation> lettreMotivations, List<SejourAcademique> sejourAcademiques, List<Message> messages, Parcours parcours, List<Cours> cours) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.numeroEtudiant = numeroEtudiant;
        this.anneeScolaire = anneeScolaire;
        this.statutScolaire = statutScolaire;
        this.promotion = promotion;
        this.image = image;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
