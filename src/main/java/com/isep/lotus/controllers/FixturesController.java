package com.isep.lotus.controllers;

import com.isep.lotus.models.Eleve;
import com.isep.lotus.models.Professeur;
import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class FixturesController {

    @RequestMapping(value = "/fixtures", method = RequestMethod.GET)
    public String fixtures() {
        Eleve eleve1 = new Eleve(8740, "A2", "Académique", "2019", null, null, null, null, null, null, null, null, null);
        Utilisateur utilisateur1 = new Utilisateur("eliottdes", "aze", "Eliott", "de SEGUIER", "eliottdes@gmail.com", null, eleve1, null);

        Professeur professeur1 = new Professeur(null, null, null);
        Utilisateur utilisateur2 = new Utilisateur("zakiazi", "aze", "Zakia", "KAZI-AOUL", "zakia.kazi-aoul@isep.fr", null, null, professeur1);

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(utilisateur1);
        session.persist(utilisateur2);
        tx.commit();
        session.close();


        return "home";
    }

}
