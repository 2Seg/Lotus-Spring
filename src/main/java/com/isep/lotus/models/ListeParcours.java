/* Liste déroulante des différents parcours de l'ISEP */

package com.isep.lotus.models;

import javax.persistence.Entity;

@Entity(name = "liste_parcours")
public class ListeParcours {

    private int id;

    private String nom;


    public ListeParcours(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
