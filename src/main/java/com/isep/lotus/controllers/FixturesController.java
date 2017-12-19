package com.isep.lotus.controllers;

import com.isep.lotus.models.Eleve;
import com.isep.lotus.models.Professeur;
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

        Eleve eleve1 = new Eleve("Eliott", "de SEGUIER", "eliottdes@gmail.com", 8740, "A2", "Académique", "2019", null, null, null, null, null, null, null, null, null, null);
        Eleve eleve2 = new Eleve("Quentin", "ANDRIEU", "andrieuquentin8@gmail.com", 8886, "A2", "Académique", "2019", null, null, null, null, null, null, null, null, null, null);
        Eleve eleve3 = new Eleve("Francisco", "de La VEGA", "franciscodelavega@isep.fr", 94503, "A1", "Alternance", "2010", null, null, null, null, null, null, null, null, null, null);

        Professeur professeur1 = new Professeur("Zakia", "Kazi-Aoul", "zakia.kazi-aoul@isep.fr", null, null, null, null);

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(eleve1);
        session.persist(eleve2);
        session.persist(eleve3);
        session.persist(professeur1);
        tx.commit();
        session.close();


        return "home";
    }

}
