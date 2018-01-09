package com.isep.lotus.controllers;

import com.isep.lotus.models.Cours;
import com.isep.lotus.models.Parcours;
import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @ModelAttribute("erreur")
    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public ModelAndView editProfileDisplay(HttpSession httpSession, ModelAndView modelAndView, @RequestParam(value = "erreur", required = false) String erreur, @RequestParam(value = "message", required = false) String message) {
        if(httpSession.isNew()) {
            return new ModelAndView("login");
        }

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        if (utilisateur == null) {
            return new ModelAndView("/login");
        }

        if (erreur != null) {
            modelAndView.addObject("erreur", erreur);
        }
        if (message != null) {
            modelAndView.addObject("message", message);
        }

        if (httpSession.getAttribute("type") == "professeur") {

            modelAndView.addObject(utilisateur);
            modelAndView.addObject("listParcours", sessionHibernate.createQuery("select p from parcours p").list());
            modelAndView.addObject("listParcoursUtilisateur", utilisateur.getProfesseur().getParcours());
            modelAndView.addObject("listCours", sessionHibernate.createQuery("select c from cours c").list());
            modelAndView.addObject("listCoursUtilisateur", utilisateur.getProfesseur().getCours());


        } else if (httpSession.getAttribute("type") == "eleve") {

            modelAndView.addObject(utilisateur);
            modelAndView.addObject("listParcours", sessionHibernate.createQuery("select p from parcours p").list());
            modelAndView.addObject("listParcoursUtilisateur", utilisateur.getEleve().getParcours());
            modelAndView.addObject("listCours", sessionHibernate.createQuery("select c from cours c").list());
            modelAndView.addObject("listCoursUtilisateur", utilisateur.getEleve().getCours());
            modelAndView.addObject("listActivite", sessionHibernate.createQuery("select a from activite a").list());
            modelAndView.addObject("listAnneeScolaire", sessionHibernate.createQuery("select s from annee_scolaire s").list());

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
        String message = "Modifications effectuées";
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
        modelAndView.addObject("message", message).setViewName("redirect:/profile/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit/connection-info", method = RequestMethod.POST)
    public ModelAndView editConnectionInfoProcess(@RequestParam("identifiant") String pseudo,
                                                  @RequestParam("mdp") String password,
                                                  @RequestParam("conf-mdp") String confPassword,
                                                  HttpSession httpSession,
                                                  ModelAndView modelAndView) {

        String erreur = "Erreur : ";
        String message = "Modifications effectuées";
        String identifiant = secureFieldString(pseudo);
        String mdp = passWordEncryption(password);
        String confMdp = passWordEncryption(confPassword);

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        modelAndView.addObject(utilisateur);

        if (identifiant.isEmpty()) {
            erreur = erreur + "veuillez remplir tous les champs marqués d'une astérisque";
            modelAndView.addObject("erreur", erreur).setViewName("redirect:/profile/edit");
            return modelAndView;
        } else {

            if(!mdp.isEmpty()) {
                if (!mdp.equals(confMdp)) {
                    erreur = erreur + "le mot de passe et sa confirmation ne correspondent pas";
                    modelAndView.addObject("erreur", erreur).setViewName("redirect:/profile/edit");
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
        modelAndView.addObject("message", message).setViewName("redirect:/profile/edit");
        return modelAndView;
    }


    /********************************************* PARCOURS ***************************************************/

    @RequestMapping(value = "/profile/edit/specialty/add", method = RequestMethod.GET)
    public ModelAndView addParcoursDisplay(ModelAndView modelAndView, HttpSession httpSession, @RequestParam(value = "erreur", required = false) String erreur, @RequestParam(value = "message", required = false) String message) {
        if(httpSession.isNew()) {return new ModelAndView("login");}
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        if (utilisateur == null || utilisateur.checkUserType().equals("none")) {return new ModelAndView("/login");}

        if (erreur != null) {
            modelAndView.addObject("erreur", erreur);
        }
        if (message != null) {
            modelAndView.addObject("message", message);
        }

        modelAndView.addObject("listParcours", sessionHibernate.createQuery("select p from parcours p").list());
        modelAndView.addObject(utilisateur).setViewName("add-parcours");

        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit/specialty/add", method = RequestMethod.POST)
    public ModelAndView addParcoursProcess(@RequestParam("parcours") String parcoursReq,
                                           HttpSession httpSession,
                                           ModelAndView modelAndView) {

        String erreur = "Erreur : ";
        String message = "Parcours ajouté";
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        if (parcoursReq.equals("unselected")) {
            erreur = erreur + "veuillez sélectionner une option";
            modelAndView.addObject("erreur", erreur).setViewName("redirect:/profile/edit/specialty/add");
            return modelAndView;
        } else {
            Parcours parcours = (Parcours) sessionHibernate.get(Parcours.class, new Integer(parcoursReq));

            if (utilisateur.checkUserType() == "professeur") {
                utilisateur.getProfesseur().addParcours(parcours);
            } else if (utilisateur.checkUserType() == "eleve") {
                utilisateur.getEleve().addParcours(parcours);
            }


            sessionHibernate.beginTransaction();
            sessionHibernate.update(utilisateur);
            sessionHibernate.update(parcours);
            sessionHibernate.getTransaction().commit();

            modelAndView.addObject(utilisateur).addObject("message", message);
            modelAndView.setViewName("redirect:/profile/edit");

            return modelAndView;
        }

    }

    @RequestMapping(value = "/profile/edit/specialty/delete/{idParcours}", method = RequestMethod.GET)
    public ModelAndView deleteParcoursProcess(@PathVariable("idParcours") String idParcours,
                                              HttpSession httpSession,
                                              ModelAndView modelAndView) {

        String message = "Parcours supprimé";

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        Parcours parcours = (Parcours) sessionHibernate.get(Parcours.class, new Integer(idParcours));

        if (utilisateur.checkUserType() == "professeur") {
            utilisateur.getProfesseur().removeParcours(parcours);
        } else if (utilisateur.checkUserType() == "eleve") {
            utilisateur.getEleve().removeParcours(parcours);
        }

        sessionHibernate.beginTransaction();
        sessionHibernate.update(utilisateur);
        sessionHibernate.update(parcours);
        sessionHibernate.getTransaction().commit();

        modelAndView.addObject(utilisateur).addObject("message", message);
        modelAndView.setViewName("redirect:/profile/edit");

        return modelAndView;
    }


    /********************************************* COURS ***************************************************/

    @RequestMapping(value = "/profile/edit/courses/add", method = RequestMethod.GET)
    public ModelAndView addCoursDisplay(ModelAndView modelAndView, HttpSession httpSession, @RequestParam(value = "erreur", required = false) String erreur, @RequestParam(value = "message", required = false) String message) {
        if(httpSession.isNew()) {return new ModelAndView("login");}
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        if (utilisateur == null || utilisateur.checkUserType().equals("none")) {return new ModelAndView("/login");}

        if (erreur != null) {
            modelAndView.addObject("erreur", erreur);
        }
        if (message != null) {
            modelAndView.addObject("message", message);
        }

        modelAndView.addObject("listCours", sessionHibernate.createQuery("select c from cours c").list());
        modelAndView.addObject(utilisateur).setViewName("add-cours");

        return modelAndView;
    }

    @RequestMapping(value = "/profile/edit/courses/add", method = RequestMethod.POST)
    public ModelAndView addCoursProcess(@RequestParam("cours") String coursReq,
                                        HttpSession httpSession,
                                        ModelAndView modelAndView) {

        String erreur = "Erreur : ";
        String message = "Cours ajouté";
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        if (coursReq.equals("unselected")) {
            erreur = erreur + "veuillez sélectionner une option";
            modelAndView.addObject("erreur", erreur).setViewName("redirect:/profile/edit/courses/add");
            return modelAndView;
        } else {
            Cours cours = (Cours) sessionHibernate.get(Cours.class, new Integer(coursReq));

            if (utilisateur.checkUserType() == "professeur") {
                utilisateur.getProfesseur().addCours(cours);
            } else if (utilisateur.checkUserType() == "eleve") {
                utilisateur.getEleve().addCours(cours);
            }

            sessionHibernate.beginTransaction();
            sessionHibernate.update(utilisateur);
            sessionHibernate.update(cours);
            sessionHibernate.getTransaction().commit();

            modelAndView.addObject(utilisateur).addObject("message", message);
            modelAndView.setViewName("redirect:/profile/edit");

            return modelAndView;
        }
    }

    @RequestMapping(value = "/profile/edit/courses/delete/{idCours}", method = RequestMethod.GET)
    public ModelAndView deleteCoursProcess(@PathVariable("idCours") String idCours,
                                           HttpSession httpSession,
                                           ModelAndView modelAndView) {

        String message = "Cours supprimé";

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        Cours cours = (Cours) sessionHibernate.get(Cours.class, new Integer(idCours));

        if (utilisateur.checkUserType() == "professeur") {
            utilisateur.getProfesseur().removeCours(cours);
        } else if (utilisateur.checkUserType() == "eleve") {
            utilisateur.getEleve().removeCours(cours);
        }

        sessionHibernate.beginTransaction();
        sessionHibernate.update(utilisateur);
        sessionHibernate.update(cours);
        sessionHibernate.getTransaction().commit();

        modelAndView.addObject(utilisateur).addObject("message", message);
        modelAndView.setViewName("redirect:/profile/edit");

        return modelAndView;
    }

    /********************************************* INFO SCOLAIRES ***************************************************/

    @RequestMapping(value = "/profile/edit/school-info", method = RequestMethod.POST)
    public ModelAndView editSchoolInfoProcess(@RequestParam("numeroEtudiant") int numeroEtudiantReq,
                                              @RequestParam("anneeScolaire") String anneeScolaireReq,
                                              @RequestParam("promotion") String promotionReq,
                                              @RequestParam("activite") String activiteReq,
                                              ModelAndView modelAndView,
                                              HttpSession httpSession) {

        String erreur = "Erreur : ";
        String message = "Modifications effectuées";
        int numeroEtudiant = numeroEtudiantReq;
        String anneeScolaire = secureFieldString(anneeScolaireReq);
        String promotion = secureFieldString(promotionReq);
        String activite = secureFieldString(activiteReq);

        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        modelAndView.addObject(utilisateur);

        if (anneeScolaire.isEmpty() || promotion.isEmpty() || activite.isEmpty()) {
            erreur = erreur + "veuillez remplir tous les champs marqués d'une astérisque";
            modelAndView.addObject("erreur", erreur).setViewName("redirect:/profile/edit");
            return modelAndView;
        } else {

        }





        return modelAndView;
    }



    private String secureFieldString (String inputString) {
        return escapeHtml4(inputString.trim());
    }

    private String nameProcess (String inputString) {
        return inputString.toUpperCase();
    }

    private String firstNameProcess (String inputString) {
        if (inputString.isEmpty()) {
            return inputString;
        }
        char[] char_table = inputString.toLowerCase().toCharArray();
        char_table[0] = Character.toUpperCase(char_table[0]);
        return new String(char_table);
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

    private static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }
}
