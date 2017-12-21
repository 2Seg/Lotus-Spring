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
public class UtilisateurController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpSession httpSession, ModelAndView modelAndView) {
        if(httpSession.isNew()) {
            return new ModelAndView("login");
        }
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.createQuery("select u " +
                "from utilisateur u " +
                "where u.id like :id")
                .setParameter("id", httpSession.getAttribute("id"))
                .uniqueResult();
        sessionHibernate.close();
        modelAndView.addObject(utilisateur);

        switch (utilisateur.checkUserType()) {
            case "professeur":
                modelAndView.setViewName("PageAccueilProfesseur");
                return modelAndView;

            case "eleve":
                modelAndView.setViewName("PageAccueilEleve");
                return modelAndView;

            default:
                modelAndView.setViewName("PageAccueilUtilisateur");
                return modelAndView;
        }
    }


}