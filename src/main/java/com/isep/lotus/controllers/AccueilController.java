package com.isep.lotus.controllers;

import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class AccueilController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpSession httpSession, ModelAndView modelAndView) {
        if(httpSession.isNew() || httpSession.getAttribute("id") == null) {
            return new ModelAndView("redirect:/login");
        }
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        if (utilisateur == null) {
            return new ModelAndView("/login");
        }

        modelAndView.addObject("listAnneeScolaire", sessionHibernate.createQuery("select s from annee_scolaire s").list());
        modelAndView.addObject("listActivite", sessionHibernate.createQuery("select a from activite a").list());
        modelAndView.addObject("listParcours", sessionHibernate.createQuery("select p from parcours p").list());

        if (httpSession.getAttribute("type") == "professeur") {
            String parcours = utilisateur.getProfesseur().getParcours().get(0).getNom();
            String hrefCurrent = "/search?saisie=&annee=A2&parcours=" + parcours;
            String hrefOld = "/search?saisie=&annee=Diplômé&parcours=" + parcours +"&activite=Diplômé";

            modelAndView.addObject("hrefCurrent", hrefCurrent);
            modelAndView.addObject("hrefOld", hrefOld);
        }


        sessionHibernate.close();

        modelAndView.addObject(utilisateur);
        modelAndView.setViewName("accueil");
        return modelAndView;
    }

    @RequestMapping(value = "/legal-notice", method = RequestMethod.GET)
    public ModelAndView mentionsLégales(ModelAndView modelAndView) {
        modelAndView.setViewName("mentions-légales");
        return modelAndView;
    }

}
