package com.isep.lotus.controllers;

import com.isep.lotus.models.Parcours;
import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class ProfilController {

    @RequestMapping(value = "/profil", method = RequestMethod.GET)
    public ModelAndView profilDisplay(HttpSession httpSession, ModelAndView modelAndView) {
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




        }

        modelAndView.setViewName("/profil");
        return modelAndView;
    }

}
