package com.isep.lotus.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginDisplay() {return "login";}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginHandling(@RequestParam("identifiant") String identifiant, Model model) {

        model.addAttribute("identifiant", identifiant);

        return "home";
    }

}
