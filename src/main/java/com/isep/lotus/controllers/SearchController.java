package com.isep.lotus.controllers;

import com.isep.lotus.models.Eleve;
import com.isep.lotus.models.Utilisateur;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.isep.lotus.LotusApplication.getSession;
import static org.unbescape.html.HtmlEscape.escapeHtml4;

@Controller
public class SearchController {

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView searchResultsDisplay(@RequestParam(value = "saisie", required = false) String saisie,
                                             @RequestParam(value = "promotion", required = false) String promotion,
                                             @RequestParam(value = "annee", required = false) String anneeScolaire,
                                             @RequestParam(value = "activite", required = false) String activite,
                                             @RequestParam(value = "parcours", required = false) String parcours,
                                             ModelAndView modelAndView,
                                             HttpSession httpSession) {

        if(httpSession.isNew()) {return new ModelAndView("login");}
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        if (utilisateur == null) {return new ModelAndView("/login");}

        modelAndView.addObject(utilisateur);

        List<Utilisateur> utilisateurList = new ArrayList<>();

        String querySelect;
        String queryWhere;

        if (saisie.replace(" ", "").equals("") && promotion == null && anneeScolaire == null && activite == null && parcours == null) {

            modelAndView.addObject("listUtilisateur", utilisateurList);

        } else {
            querySelect = "select u from utilisateur u ";
            queryWhere = "where u.dtype like 'Utilisateur' ";

            if (saisie != "") {
                queryWhere = queryWhere +
                        "and u.email like :saisie or " +
                        "u.identifiant like :saisie or " +
                        "u.nom like :saisie or " +
                        "u.prenom like :saisie or " +
                        "u.activite like :saisie or " +
                        "u.anneeScolaire like :saisie or " +
                        "u.numeroEtudiant like :saisie or " +
                        "u.promotion like :saisie " ;
            }
            if (promotion != null) {
                queryWhere = queryWhere + "and u.promotion like :promotion";
            }
            if (anneeScolaire != null) {
                queryWhere = queryWhere + "and u.anneeScolaire like :anneeScolaire";
            }
            if (activite != null) {

            }
            if (parcours != null) {

            }

            String query = querySelect + queryWhere;
            Query queryHibernate = sessionHibernate.createQuery(query);

            if (saisie != "") {
                queryHibernate.setParameter("saisie", saisie);
                modelAndView.addObject("saisie", saisie);
            }
            if (promotion != null) {
                queryHibernate.setParameter("promotion", promotion);
                modelAndView.addObject("promotion", promotion);
            }
            if (anneeScolaire != null) {
                queryHibernate.setParameter("anneeScolaire", anneeScolaire);
                modelAndView.addObject("anneeScolaire", anneeScolaire);
            }
            if (activite != null) {

                modelAndView.addObject("activite", activite);
            }
            if (parcours != null) {

                modelAndView.addObject("parcours", parcours);
            }

            System.out.println(query);

            // Let me introduce you la boucle qui fait tout marcher <3
            for (Object object : queryHibernate.list()) {
                if (object.getClass().equals(Eleve.class)) {
                    Eleve eleve = (Eleve) object;
                    utilisateurList.add(eleve.getUtilisateur());
                } else if (object.getClass().equals(Utilisateur.class)) {
                    Utilisateur user = (Utilisateur) object;
                    utilisateurList.add(user);
                }
            }

            modelAndView.addObject("listUtilisateur", utilisateurList);

        }

        modelAndView.setViewName("search");
        return modelAndView;
    }



    public String secureFieldString (String inputString) {
        return escapeHtml4(inputString.trim());
    }

}
