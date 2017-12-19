package com.isep.lotus.controllers;

import com.isep.lotus.models.Bulletin;
import com.isep.lotus.models.Eleve;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.isep.lotus.LotusApplication.getSession;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {

        Eleve eleve = new Eleve(1,"Francis", "JOSSELAIN", "eliottdes@gmail.com", true, 8740, "A2", "Académique", "2019", null, null, null, null, null, null, null, null, null, null, null);
//        model.addAllAttributes(Collections.singleton(eleve));

        model.addAttribute(eleve);
        return "home";
    }

}
