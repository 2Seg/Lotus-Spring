package com.isep.lotus.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "activite_profesionnelle")
public class ActivitePro {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "eleve_id", foreignKey = @ForeignKey(name = "ELEVE_ACTIVITE_PRO_FK"))
    private Eleve eleve;

    @JoinColumn(name = "contrat_id")
    private ListeTypeContrat type;

    private String duree;

    private Date dateDebut;

    private Date dateFin;

    private String nomEntreprise;

    private String domaineActivite;

    private String poste;

    private String description;

    private String pays;

    private String ville;

    // TODO : préciser les attributs ci-dessous
    /*private String objectif;

    private String etape;

    private String connaissanceRequise;*/


    public ActivitePro(int id, Eleve eleve, ListeTypeContrat type, String duree, Date dateDebut, Date dateFin, String nomEntreprise, String domaineActivite, String poste, String description, String pays, String ville) {
        this.id = id;
        this.eleve = eleve;
        this.type = type;
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

    public ListeTypeContrat getType() {
        return type;
    }

    public void setType(ListeTypeContrat type) {
        this.type = type;
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
