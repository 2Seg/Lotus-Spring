package com.isep.lotus.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ.
 * User:  eliott
 * Date: 14/12/17
 * Time: 11:49
 */

@Entity(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable = false)
    protected String identifiant;

    @JoinColumn(nullable = false)
    protected String mdp;

    @JoinColumn(nullable = false)
    protected String prenom;

    @JoinColumn(nullable = false)
    protected String nom;

    @JoinColumn(nullable = false)
    protected String email;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Image image;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Eleve eleve;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Professeur professeur;

    @ManyToMany(mappedBy = "utilisateurs", cascade = {CascadeType.PERSIST})
    private List<Parcours> parcours = new ArrayList<>();

    @ManyToMany(mappedBy = "utilisateurs", cascade = {CascadeType.PERSIST})
    private List<Cours> cours = new ArrayList<>();


    public Utilisateur() {}

    public Utilisateur(String identifiant, String mdp, String prenom, String nom, String email) {
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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

    public List<Parcours> getParcours() {
        return parcours;
    }

    public void addParcours(Parcours parcoursToAdd) {
        parcours.add(parcoursToAdd);
        parcoursToAdd.getUtilisateurs().add(this);
    }

    public void removeParcours(Parcours parcoursToRemove) {
        parcours.remove(parcoursToRemove);
        parcoursToRemove.getUtilisateurs().remove(this);
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void addCours(Cours coursToAdd) {
        cours.add(coursToAdd);
        coursToAdd.getUtilisateurs().add(this);
    }

    public void removeCours(Cours coursToRemove) {
        cours.remove(coursToRemove);
        coursToRemove.getUtilisateurs().remove(this);
    }

    public String checkUserType () {
        if (this.getProfesseur() == null) {
            if (this.getEleve() == null) {
                return "none";
            } else {
                return "eleve";
            }
        } else {
            return "professeur";
        }
    }

}
