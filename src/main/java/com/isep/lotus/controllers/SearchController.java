package com.isep.lotus.controllers;

import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class SearchController {

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView searchResultsDisplay(@RequestParam(value = "saisie", required = false) String saisie,
                                             @RequestParam(value = "promotion", required = false) String promotion,
                                             @RequestParam(value = "annee", required = false) String annee,
                                             @RequestParam(value = "activite", required = false) String activite,
                                             @RequestParam(value = "parcours", required = false) String parcours,
                                             ModelAndView modelAndView,
                                             HttpSession httpSession) {

        if(httpSession.isNew()) {return new ModelAndView("login");}
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        if (utilisateur == null) {return new ModelAndView("/login");}

        modelAndView.addObject(utilisateur);

        if (saisie != null) {
            modelAndView.addObject("saisie", saisie);
        }
        if (promotion != null) {
            modelAndView.addObject("promotion", promotion);
        }
        if (annee != null) {
            modelAndView.addObject("annee", annee);
        }
        if (activite != null) {
            modelAndView.addObject("activite", activite);
        }
        if (parcours != null) {
            modelAndView.addObject("parcours", parcours);
        }

        modelAndView.setViewName("search");
        return modelAndView;
    }

}
