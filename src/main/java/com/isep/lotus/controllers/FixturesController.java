package com.isep.lotus.controllers;

import com.isep.lotus.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class FixturesController {

    @RequestMapping(value = "/fixtures1", method = RequestMethod.GET)
    public ModelAndView fixtures1(ModelAndView modelAndView) {

//        Parcours si = new Parcours("Systèmes d'information");
        Parcours bi = new Parcours("Bussiness Intelligence");
        Parcours gl = new Parcours("Génie Logiciel");
        Parcours ns = new Parcours("Numérique et santé");
        Parcours iot = new Parcours("Télécommunications et IOT");
        Parcours res = new Parcours("Sécurité numérique et Réseaux");
        Parcours se = new Parcours("Systèmes embarqués");
        Parcours cre = new Parcours("Innovation et Création d'entreprise");

//        Session sessionHibernate = getSession();
//        Parcours si = (Parcours) sessionHibernate.get(Parcours.class, 9);
//        sessionHibernate.close();

//        Eleve eleve1 = new Eleve(8740, "A2", "Académique", "2019", true, "http://linkedin.com/eliott-de-seguier", null, null, null, null, null, null, null, null, null);
//        Utilisateur utilisateur1 = new Utilisateur("eliottdes", passWordEncryption("aze"), "Eliott", "DE SEGUIER", "eliottdes@gmail.com", null, eleve1, null);
//
//        Utilisateur utilisateur2 = new Utilisateur("manoniri", passWordEncryption("aze"), "Manon", "IRIBARNE", "manon.iribarne@isep.fr", null, null, null);
//
//        Utilisateur utilisateur3 = new Utilisateur("oriajo", passWordEncryption("aze"), "Orianne", "JOANNIC", "orianne.joannic@isep.fr", null, null, null);
//
//        Utilisateur utilisateur4 = new Utilisateur("quent77np", passWordEncryption("aze"), "Quentin", "ANDRIEU", "quentin.andrieu@isep.fr", null, null, null);
//
//        Professeur professeur1 = new Professeur();
//        professeur1.getParcours().add(si);
//
//        Utilisateur utilisateur5 = new Utilisateur("zakiazi", passWordEncryption("aze"), "Zakia", "KAZI-AOUL", "zakia.kazi-aoul@isep.fr");
//        utilisateur5.setProfesseur(professeur1);

//        Utilisateur utilisateur6 = new Utilisateur("herfel", passWordEncryption("aze"), "Hervé", "FELLER", "herve.feller@isep.fr", null, null, null);
//        utilisateur5.getProfesseur().getParcours().get(0).getNom();


        Session session = getSession();
        Transaction tx = session.beginTransaction();

//        session.persist(si);
        session.persist(bi);
        session.persist(gl);
        session.persist(ns);
        session.persist(iot);
        session.persist(res);
        session.persist(se);
        session.persist(cre);

//        session.persist(utilisateur1);
//        session.persist(utilisateur2);
//        session.persist(utilisateur3);
//        session.persist(utilisateur4);
//        session.persist(utilisateur5);
//        session.persist(utilisateur6);
        tx.commit();
        session.close();

        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/fixtures2", method = RequestMethod.GET)
    public ModelAndView fixtures2(ModelAndView modelAndView) {

        Session session = getSession();
        Parcours si = (Parcours) session.get(Parcours.class, 1);
        Cours technoWeb = (Cours) session.get(Cours.class, 1);

        Eleve eleve = new Eleve(8740, "A2", "Académique", "2019", true, "www.linkedin.com/in/eliott-de-séguier");
        Utilisateur utilisateur = new Utilisateur("eliottdes", passWordEncryption("aze"), "Eliott", "DE SEGUIER", "eliottdes@gmail.com");

        eleve.addParcours(si);
        eleve.addCours(technoWeb);
        utilisateur.setEleve(eleve);



        session.beginTransaction();
        session.persist(utilisateur);

        session.getTransaction().commit();

        session.close();

        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @RequestMapping(value = "/fixtures3", method = RequestMethod.GET)
    public ModelAndView fixtures3(ModelAndView modelAndView) {

        Parcours si = new Parcours("Systèmes d'information");

        Cours technoWeb = new Cours("Technologies Web");
        Cours archiSi = new Cours ("Architectures des Systèmes d'information");

        Professeur professeur = new Professeur();
        Utilisateur utilisateur = new Utilisateur("zakiazi", passWordEncryption("aze"), "Zakia", "KAZI-AOUL", "zakia.kazi-aoul@isep.fr");

        professeur.addParcours(si);
        professeur.addCours(technoWeb);
        professeur.addCours(archiSi);
        utilisateur.setProfesseur(professeur);


        Session session = getSession();
        session.beginTransaction();
        session.persist(utilisateur);

        session.getTransaction().commit();

        session.close();



        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    public String passWordEncryption(String password) {
        if (password.isEmpty()) {
            return password;
        }
        String outputPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            outputPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return outputPassword;
    }

}
