package com.isep.lotus.controllers;

import com.isep.lotus.models.Parcours;
import com.isep.lotus.models.Utilisateur;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static com.isep.lotus.LotusApplication.getSession;
import static org.unbescape.html.HtmlEscape.escapeHtml4;

@Controller
public class ProfilController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profileDisplay(HttpSession httpSession, ModelAndView modelAndView) {
        if(httpSession.isNew()) {
            return new ModelAndView("login");
        }

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        if (utilisateur == null) {
            return new ModelAndView("/login");
        }

        modelAndView.addObject(utilisateur);

        if (httpSession.getAttribute("type") == "professeur") {

            modelAndView.addObject("listParcours", utilisateur.getProfesseur().getParcours());
            modelAndView.addObject("listCours", utilisateur.getProfesseur().getCours());

        } else if (httpSession.getAttribute("type") == "eleve") {

            modelAndView.addObject("listParcours", utilisateur.getEleve().getParcours());
            modelAndView.addObject("listCours", utilisateur.getEleve().getCours());


        }



        modelAndView.setViewName("/profile");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public ModelAndView editProfileDisplay(HttpSession httpSession, ModelAndView modelAndView) {
        if(httpSession.isNew()) {
            return new ModelAndView("login");
        }

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        if (utilisateur == null) {
            return new ModelAndView("/login");
        }

        modelAndView.addObject(utilisateur);

        if (httpSession.getAttribute("type") == "professeur") {

            modelAndView.addObject("listParcours", sessionHibernate.createQuery("select p from parcours p").list());

        } else if (httpSession.getAttribute("type") == "eleve") {




        }



        modelAndView.setViewName("/edit-profile");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public ModelAndView editProfileProcess(@RequestParam("prenom") String firstname,
                                           @RequestParam("nom") String name,
                                           @RequestParam("email") String mail,
                                           @RequestParam("identifiant") String pseudo,
                                           @RequestParam("mdp") String password,
                                           @RequestParam("conf-mdp") String confPassword,
                                           ModelAndView modelAndView,
                                           HttpSession httpSession) {

        String erreur = "Erreur : ";
        String prenom = firstNameProcess(firstname);
        String nom = nameProcess(name);
        String email = secureFieldString(mail);
        String identifiant = secureFieldString(pseudo);
        String mdp = passWordEncryption(password);
        String confMdp = passWordEncryption(confPassword);

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        modelAndView.addObject(utilisateur);

        if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || identifiant.isEmpty()) {
            erreur = erreur + "veuillez remplir tous les champs marqués d'une astérisque";
            modelAndView.addObject("erreur", erreur).setViewName("edit-profile");
            return modelAndView;
        } else {

            if(!mdp.isEmpty()) {
                if (!mdp.equals(confMdp)) {
                    erreur = erreur + "le mot de passe et sa confirmation ne correspondent pas";
                    modelAndView.addObject("erreur", erreur).setViewName("edit-profile");
                    return modelAndView;
                } else {
                    utilisateur.setPrenom(prenom);
                    utilisateur.setNom(nom);
                    utilisateur.setEmail(email);
                    utilisateur.setIdentifiant(identifiant);
                    utilisateur.setMdp(mdp);

                    sessionHibernate.beginTransaction();
                    sessionHibernate.update(utilisateur);
                    sessionHibernate.getTransaction().commit();
                }

            } else {
                utilisateur.setPrenom(prenom);
                utilisateur.setNom(nom);
                utilisateur.setEmail(email);
                utilisateur.setIdentifiant(identifiant);

                sessionHibernate.beginTransaction();
                sessionHibernate.update(utilisateur);
                sessionHibernate.getTransaction().commit();
            }

        }

        sessionHibernate.close();
        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }




    public String secureFieldString (String inputString) {
        return escapeHtml4(inputString.trim());
    }

    public String nameProcess (String inputString) {
        return inputString.toUpperCase();
    }

    public String firstNameProcess (String inputString) {
        if (inputString.isEmpty()) {
            return inputString;
        }
        char[] char_table = inputString.toLowerCase().toCharArray();
        char_table[0] = Character.toUpperCase(char_table[0]);
        return new String(char_table);
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
