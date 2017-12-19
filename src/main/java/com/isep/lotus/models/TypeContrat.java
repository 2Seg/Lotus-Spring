/* Liste déroulante des différents types de contrats de l'activité profesionnelle d'un élève */

package com.isep.lotus.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "type_contrat")
public class TypeContrat {

    @Id
    @GeneratedValue
    private int id;

    private String typeContrat;


    public TypeContrat() {}

    public TypeContrat(int id, String typeContrat) {
        this.id = id;
        this.typeContrat = typeContrat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }
}
