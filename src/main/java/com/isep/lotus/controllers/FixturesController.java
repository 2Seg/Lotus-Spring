package com.isep.lotus.controllers;

import com.isep.lotus.models.Eleve;
import com.isep.lotus.models.Professeur;
import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class FixturesController {

    @RequestMapping(value = "/fixtures", method = RequestMethod.GET)
    public ModelAndView fixtures() {
        Eleve eleve1 = new Eleve(8740, "A2", "Académique", "2019", null, null, null, null, null, null, null, null, null);
        Utilisateur utilisateur1 = new Utilisateur("eliottdes", passWordEncryption("aze"), "Eliott", "de SEGUIER", "eliottdes@gmail.com", null, eleve1, null);

        Professeur professeur1 = new Professeur(null, null, null);
        Utilisateur utilisateur2 = new Utilisateur("zakiazi", passWordEncryption("aze"), "Zakia", "KAZI-AOUL", "zakia.kazi-aoul@isep.fr", null, null, professeur1);

        Utilisateur utilisateur3 = new Utilisateur("quent77np", passWordEncryption("aze"), "Quentin", "ANDRIEU", "quentin.andrieu@isep.fr", null, null, null);

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(utilisateur1);
        session.persist(utilisateur2);
        session.persist(utilisateur3);
        tx.commit();
        session.close();

        return new ModelAndView("redirect:/");
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
