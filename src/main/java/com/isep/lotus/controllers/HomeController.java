package com.isep.lotus.controllers;

import com.isep.lotus.models.Bulletin;
import com.isep.lotus.models.Eleve;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

//    @RequestMapping("/")
//    public String index(Model model, HttpSession httpSession) {
//        if(httpSession.isNew()) {return "login";}
//        return "home";
//    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/profil-professeur")
    public String profil_professeur() {
       /* Professeur professeur = new Professeur(1,"Zakia", "Kazi-Aoul", "zakia@gmail.com");
//        model.addAllAttributes(Collections.singleton(professeur));
        model.addAttribute(professeur);*/
        return "profil_professeur";
    }

    @RequestMapping("/profil-eleve")
    public String profil_eleve() {
        return "profil_eleve";
    }

    @RequestMapping("/ModificationProfilProfesseur")
    public String ModificationProfilProfesseur () {
        return "ModificationProfilProfesseur";
    }

    @RequestMapping("/PageAccueilProfesseur")
    public String PageAccueilProfesseur () {
        return "PageAccueilProfesseur";
    }

    @RequestMapping("/PageAccueilEleve")
    public String PageAccueilEleve () {
        return "PageAccueilEleve";
    }

    @RequestMapping("/FormulaireProfesseur")
    public String FormulaireProfesseur () {
        return "FormulaireProfesseur";
    }

    @RequestMapping("/formulaire-eleve")
    public String formulaire_eleve () { return "formulaire_eleve"; }

    @RequestMapping("/modification-profil-eleve")
    public String modification_profil_eleve () { return "modification_profil_eleve"; }

    @RequestMapping("/liste-ancien-eleve")
    public String liste_ancien_eleve () { return "liste_ancien_eleve"; }

    @RequestMapping("/Recherche-prof")
    public String Recherche_prof() { return "Recherche_prof"; }

}

