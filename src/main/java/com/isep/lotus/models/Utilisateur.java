package com.isep.lotus.models;

import javax.persistence.*;

/**
 * Created by IntelliJ.
 * User:  eliott
 * Date: 14/12/17
 * Time: 11:49
 */

// Faire des relations OneToOne avec Eleve et Professeur
//@Entity(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable = false)
    private String prenom;

    @JoinColumn(nullable = false)
    private String nom;

    @JoinColumn(nullable = false)
    private String email;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Eleve eleve;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Professeur professeur;
}
