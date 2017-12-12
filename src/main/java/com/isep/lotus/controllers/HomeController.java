package com.isep.lotus.controllers;

import com.isep.lotus.models.Eleve;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        Eleve eleve = new Eleve(1,"Eliott", "de SEGUIER", "eliottdes@gmail.com", true, 8740, "A2", "Académique", "2019", null, null, null, null, null, null, null, null, null, null, null);
//        model.addAllAttributes(Collections.singleton(eleve));
        model.addAttribute(eleve);
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/profil_professeur")
    public String profil_professeur() {
       /* Professeur professeur = new Professeur(1,"Zakia", "Kazi-Aoul", "zakia@gmail.com");
//        model.addAllAttributes(Collections.singleton(professeur));
        model.addAttribute(professeur);*/
        return "profil_professeur";
    }
}
