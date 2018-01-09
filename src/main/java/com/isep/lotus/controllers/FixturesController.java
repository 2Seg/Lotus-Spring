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

    @RequestMapping(value = "/fixtures", method = RequestMethod.GET)
    public ModelAndView fixtures1(ModelAndView modelAndView) {

        Parcours si = new Parcours("Systèmes d'information");
        Parcours bi = new Parcours("Bussiness Intelligence");
        Parcours gl = new Parcours("Génie Logiciel");
        Parcours ns = new Parcours("Numérique et santé");
        Parcours iot = new Parcours("Télécommunications et IOT");
        Parcours res = new Parcours("Sécurité numérique et Réseaux");
        Parcours se = new Parcours("Systèmes embarqués");
        Parcours cre = new Parcours("Innovation et Création d'entreprise");

        Cours technoWeb = new Cours("Technologies web");
        Cours archiSi = new Cours("Architecture des Systèmes d'Information");
        Cours data = new Cours("Base de données et Big Data");
        Cours langue = new Cours("Langues et Culture");
        Cours cyber = new Cours("Cybersécurité");
        Cours glog = new Cours("Génie Logiciel");
        Cours perso = new Cours("Engagement Personnel");
        Cours elec = new Cours("Electronique");

        Utilisateur eliott = new Utilisateur("eliottdes", passWordEncryption("aze"), "Eliott", "DE SEGUIER", "eliottdes@gmail.com");
        Eleve eliottEleve = new Eleve(8740, "A2", "Académique", "2019", true, "www.linkedin.com/in/eliott-de-séguier");
        eliott.setEleve(eliottEleve);
        eliott.getEleve().addParcours(si);
        eliott.getEleve().addCours(technoWeb);
        eliott.getEleve().addCours(archiSi);

        Utilisateur manon = new Utilisateur("manoniri", passWordEncryption("aze"), "Manon", "IRIBARNE", "manon.iribarne@isep.fr");
        Eleve manonEleve = new Eleve(5397, "A1", "Alternance", "2020", false, "www.linkedin.com/in/manon-iribarne");
        manon.setEleve(manonEleve);
        manon.getEleve().addParcours(si);
        manon.getEleve().addCours(data);

        Utilisateur quentin =  new Utilisateur("quent77np", passWordEncryption("aze"), "Quentin", "ANDRIEU", "quentin.andrieu@isep.fr");
        Eleve quentinEleve = new Eleve(3627, "A2", "Académique", "2019", false, "www.linkedin.com/in/quentin-andrieu");
        quentin.setEleve(quentinEleve);
        quentin.getEleve().addParcours(bi);
        quentin.getEleve().addCours(data);
        quentin.getEleve().addCours(archiSi);

        Utilisateur orianne =  new Utilisateur("oriannejo", passWordEncryption("aze"), "Orianne", "JOANNIC", "orianne.joannic@isep.fr");




        Utilisateur zakia = new Utilisateur("zakiazi", passWordEncryption("aze"), "Zakia", "KAZI-AOUL", "zakia.kazi-aoul@isep.fr");
        Professeur zakiaProfesseur = new Professeur();
        zakia.setProfesseur(zakiaProfesseur);
        zakia.getProfesseur().addParcours(si);
        zakia.getProfesseur().addParcours(iot);

        Utilisateur herve = new Utilisateur("herfel", passWordEncryption("aze"), "Hervé", "FELLER", "herve.feller@isep.fr");



        Session session = getSession();
        Transaction tx = session.beginTransaction();

        session.persist(si);
        session.persist(bi);
        session.persist(gl);
        session.persist(ns);
        session.persist(iot);
        session.persist(res);
        session.persist(se);
        session.persist(cre);

        session.persist(technoWeb);
        session.persist(archiSi);
        session.persist(data);
        session.persist(langue);
        session.persist(cyber);
        session.persist(glog);
        session.persist(perso);
        session.persist(elec);

        session.persist(eliott);
        session.persist(manon);
        session.persist(quentin);
        session.persist(orianne);
        session.persist(zakia);
        session.persist(herve);

        tx.commit();
        session.close();

        return new ModelAndView("redirect:/login");
    }

    private String passWordEncryption(String password) {
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
