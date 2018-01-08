package com.isep.lotus.controllers;

import com.isep.lotus.models.Parcours;
import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        modelAndView.addObject("listParcours", sessionHibernate.createQuery("select p from parcours p").list());
        modelAndView.addObject("listParcoursUtilisateur", utilisateur.getProfesseur().getParcours());
        modelAndView.addObject("listCoursUtilisateur", utilisateur.getProfesseur().getCours());

        if (httpSession.getAttribute("type") == "professeur") {



        } else if (httpSession.getAttribute("type") == "eleve") {




        }



        modelAndView.setViewName("/edit-profile");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit/personal-info", method = RequestMethod.POST)
    public ModelAndView editProfileProcess(@RequestParam("prenom") String firstname,
                                           @RequestParam("nom") String name,
                                           @RequestParam("email") String mail,
                                           ModelAndView modelAndView,
                                           HttpSession httpSession) {

        String erreur = "Erreur : ";
        String prenom = firstNameProcess(firstname);
        String nom = nameProcess(name);
        String email = secureFieldString(mail);

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        modelAndView.addObject(utilisateur);

        if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty()) {
            erreur = erreur + "veuillez remplir tous les champs marqués d'une astérisque";
            modelAndView.addObject("erreur", erreur).setViewName("edit-profile");
            return modelAndView;
        } else {

            utilisateur.setPrenom(prenom);
            utilisateur.setNom(nom);
            utilisateur.setEmail(email);

            sessionHibernate.beginTransaction();
            sessionHibernate.update(utilisateur);
            sessionHibernate.getTransaction().commit();

        }

        sessionHibernate.close();
        modelAndView.setViewName("redirect:/profile/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit/connection-info", method = RequestMethod.POST)
    public ModelAndView editConnectionInfoProcess(@RequestParam("identifiant") String pseudo,
                                                  @RequestParam("mdp") String password,
                                                  @RequestParam("conf-mdp") String confPassword,
                                                  HttpSession httpSession,
                                                  ModelAndView modelAndView) {

        String erreur = "Erreur : ";
        String identifiant = secureFieldString(pseudo);
        String mdp = passWordEncryption(password);
        String confMdp = passWordEncryption(confPassword);

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        modelAndView.addObject(utilisateur);

        if (identifiant.isEmpty()) {
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
                    utilisateur.setIdentifiant(identifiant);
                    utilisateur.setMdp(mdp);

                    sessionHibernate.beginTransaction();
                    sessionHibernate.update(utilisateur);
                    sessionHibernate.getTransaction().commit();
                }

            } else {
                utilisateur.setIdentifiant(identifiant);

                sessionHibernate.beginTransaction();
                sessionHibernate.update(utilisateur);
                sessionHibernate.getTransaction().commit();
            }

        }

        sessionHibernate.close();
        modelAndView.setViewName("redirect:/profile/edit");
        return modelAndView;
    }


    /********************************************* PARCOURS ***************************************************/

    @RequestMapping(value = "/profile/edit/specialty/add", method = RequestMethod.GET)
    public ModelAndView addParcoursDisplay(ModelAndView modelAndView, HttpSession httpSession) {
        if(httpSession.isNew()) {return new ModelAndView("login");}
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        if (utilisateur == null || utilisateur.checkUserType().equals("none")) {return new ModelAndView("/login");}

        modelAndView.addObject("listParcours", sessionHibernate.createQuery("select p from parcours p").list());
        modelAndView.addObject(utilisateur).setViewName("add-parcours");

        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit/specialty/add", method = RequestMethod.POST)
    public ModelAndView addParcoursProcess(@RequestParam("parcours") String parcoursReq,
                                           HttpSession httpSession,
                                           ModelAndView modelAndView) {

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        Parcours parcours = (Parcours) sessionHibernate.get(Parcours.class, new Integer(parcoursReq));

        utilisateur.getProfesseur().addParcours(parcours);

        sessionHibernate.beginTransaction();
        sessionHibernate.update(utilisateur);
        sessionHibernate.update(parcours);
        sessionHibernate.getTransaction().commit();

        modelAndView.addObject(utilisateur);
        modelAndView.setViewName("redirect:/profile/edit");

        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit/specialty/delete/{idParcours}", method = RequestMethod.GET)
    public ModelAndView deleteParcoursProcess(@PathVariable("idParcours") String idParcours,
                                              HttpSession httpSession,
                                              ModelAndView modelAndView) {

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        Parcours parcours = (Parcours) sessionHibernate.get(Parcours.class, new Integer(idParcours));

        utilisateur.getProfesseur().removeParcours(parcours);

        sessionHibernate.beginTransaction();
        sessionHibernate.update(utilisateur);
        sessionHibernate.update(parcours);
        sessionHibernate.getTransaction().commit();

        modelAndView.addObject(utilisateur);
        modelAndView.setViewName("redirect:/profile/edit");

        return modelAndView;
    }


    /********************************************* COURS ***************************************************/

    @RequestMapping(value = "/profile/edit/courses", method = RequestMethod.GET)
    public ModelAndView editCoursProcess(HttpSession httpSession, ModelAndView modelAndView) {
        if(httpSession.isNew()) {return new ModelAndView("login");}
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        if (utilisateur == null || utilisateur.checkUserType().equals("none")) {return new ModelAndView("/login");}

        modelAndView.addObject(utilisateur).setViewName("add-cours");


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
