package com.isep.lotus.controllers;

import com.isep.lotus.models.Bulletin;
import com.isep.lotus.models.Eleve;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class LoginController {

    @RequestMapping("/test")
    public String test() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Eleve eleve = new Eleve(1,"Eliott", "de SEGUIER", "eliottdes@gmail.com", true, 8740, "A2", "Académique", "2019", null, null, null, null, null, null, null, null, null, null, null);
        Bulletin bulletin = new Bulletin(1, eleve, "fichier");

        session.persist(bulletin);
        transaction.commit();
        session.close();

        return "home";
    }

}
