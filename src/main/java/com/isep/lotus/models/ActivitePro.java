package com.isep.lotus.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "activite_profesionnelle")
public class ActivitePro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(nullable = false)
    private Eleve eleve;

    private String typeContrat;

    private String duree;

    private Date dateDebut;

    private Date dateFin;

    private String nomEntreprise;

    private String domaineActivite;

    private String poste;

    private String description;

    private String pays;

    private String ville;


    public ActivitePro() {}

    public ActivitePro(Eleve eleve, String typeContrat, String duree, Date dateDebut, Date dateFin, String nomEntreprise, String domaineActivite, String poste, String description, String pays, String ville) {
        this.eleve = eleve;
        this.typeContrat = typeContrat;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomEntreprise = nomEntreprise;
        this.domaineActivite = domaineActivite;
        this.poste = poste;
        this.description = description;
        this.pays = pays;
        this.ville = ville;
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

    public String getType() {
        return typeContrat;
    }

    public void setType(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getDomaineActivite() {
        return domaineActivite;
    }

    public void setDomaineActivite(String domaineActivite) {
        this.domaineActivite = domaineActivite;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
