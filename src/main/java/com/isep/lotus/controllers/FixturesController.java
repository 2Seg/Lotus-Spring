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
import java.util.Date;
import java.util.List;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class FixturesController {

    @RequestMapping(value = "/fixtures", method = RequestMethod.GET)
    public ModelAndView fixtures1(ModelAndView modelAndView) {

        Activite acad = new Activite("Académique");
        Activite appre = new Activite("Apprentissage");
        Activite inter = new Activite("International");
        Activite ces = new Activite("Césure");
        Activite stage = new Activite("Stage");
        Activite dipl = new Activite("Diplômé");

        ActivitePro stage1 = new ActivitePro("Stage", 6, "TF1", "Stagiaire", null, "France", null);
        ActivitePro stage2 = new ActivitePro("Stage", 1, "Selectra", "Développeur", "Développement d'outils internes à l'entreprise", "France", "Paris");

        SejourAcademique australie = new SejourAcademique(8, 1, "Australie", "Brisbane", "A3", "Griffith University");
        SejourAcademique states = new SejourAcademique(null, null, "USA", "San Francisco", "A3", "Standford University");

        ActiviteExtra surf = new ActiviteExtra("Surf");
        ActiviteExtra piano = new ActiviteExtra("Piano");
        ActiviteExtra danse = new ActiviteExtra("Danse");
        ActiviteExtra pétanque = new ActiviteExtra("Pétanque");

        TypeContrat cdi = new TypeContrat("Contrat à Durée Indéterminée (CDI)");
        TypeContrat cdd = new TypeContrat("Contrat à Durée Déterminée (CDD)");
        TypeContrat stag = new TypeContrat("Stage");
        TypeContrat appren = new TypeContrat("Apprentissage");
        TypeContrat vie = new TypeContrat("Volontariat International en Entreprise (VIE)");
        TypeContrat bénébéné = new TypeContrat("Bénévolat");

        AnneeScolaire i1 = new AnneeScolaire("I1");
        AnneeScolaire i2 = new AnneeScolaire("I2");
        AnneeScolaire p1 = new AnneeScolaire("P1");
        AnneeScolaire p2 = new AnneeScolaire("P2");
        AnneeScolaire a1 = new AnneeScolaire("A1");
        AnneeScolaire a2 = new AnneeScolaire("A2");
        AnneeScolaire a3 = new AnneeScolaire("A3");
        AnneeScolaire ancien = new AnneeScolaire("Diplômé");

        Parcours si = new Parcours("Systèmes d'information");
        Parcours bi = new Parcours("Business Intelligence");
        Parcours gl = new Parcours("Ingénieur Logiciel");
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
        Eleve eliottEleve = new Eleve(8740, "A2", "Académique", "2019", true, "http://www.linkedin.com/in/eliott-de-séguier");
        eliott.setEleve(eliottEleve);
        eliott.getEleve().addParcours(si);
        eliott.getEleve().addCours(technoWeb);
        eliott.getEleve().addCours(archiSi);
        stage1.setEleve(eliottEleve);
        stage2.setEleve(eliottEleve);
        australie.setEleve(eliottEleve);
        surf.setEleve(eliottEleve);
        piano.setEleve(eliottEleve);

        Utilisateur manon = new Utilisateur("manoniri", passWordEncryption("aze"), "Manon", "IRIBARNE", "manon.iribarne@isep.fr");
        Eleve manonEleve = new Eleve(5397, "A1", "Apprentissage", "2020", false, "http://www.linkedin.com/in/manon-iribarne");
        manon.setEleve(manonEleve);
        manon.getEleve().addParcours(si);
        manon.getEleve().addCours(data);
        danse.setEleve(manonEleve);

        Utilisateur quentin =  new Utilisateur("quent77np", passWordEncryption("aze"), "Quentin", "ANDRIEU", "quentin.andrieu@isep.fr");
        Eleve quentinEleve = new Eleve(3627, "A2", "Académique", "2019", false, "http://www.linkedin.com/in/quentin-andrieu");
        quentin.setEleve(quentinEleve);
        quentin.getEleve().addParcours(bi);
        quentin.getEleve().addCours(data);
        quentin.getEleve().addCours(archiSi);
        states.setEleve(quentinEleve);
        pétanque.setEleve(quentinEleve);

        Utilisateur orianne =  new Utilisateur("oriannejo", passWordEncryption("aze"), "Orianne", "JOANNIC", "orianne.joannic@isep.fr");

        Utilisateur zakia = new Utilisateur("zakiazi", passWordEncryption("aze"), "Zakia", "KAZI-AOUL", "zakia.kazi-aoul@isep.fr");
        Professeur zakiaProfesseur = new Professeur();
        zakia.setProfesseur(zakiaProfesseur);
        zakia.getProfesseur().addParcours(si);
        zakia.getProfesseur().addParcours(iot);

        Utilisateur herve = new Utilisateur("herfel", passWordEncryption("aze"), "Hervé", "FELLER", "herve.feller@isep.fr");


        Session session = getSession();
        Transaction tx = session.beginTransaction();

        session.persist(acad);
        session.persist(appre);
        session.persist(inter);
        session.persist(ces);
        session.persist(stage);
        session.persist(dipl);

        session.persist(cdi);
        session.persist(cdd);
        session.persist(stag);
        session.persist(appren);
        session.persist(vie);
        session.persist(bénébéné);

        session.persist(stage1);
        session.persist(stage2);

        session.persist(australie);
        session.persist(states);

        session.persist(surf);
        session.persist(piano);
        session.persist(danse);
        session.persist(pétanque);

        session.persist(i1);
        session.persist(i2);
        session.persist(p1);
        session.persist(p2);
        session.persist(a1);
        session.persist(a2);
        session.persist(a3);
        session.persist(ancien);

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
