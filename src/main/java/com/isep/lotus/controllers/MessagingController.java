package com.isep.lotus.controllers;

import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.isep.lotus.LotusApplication.getSession;

@Controller
public class MessagingController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/messaging", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView messagingDisplay(HttpSession httpSession, ModelAndView modelAndView, @RequestParam(value = "listIdUsers", required = false) int[] listIdUsers) {
        if (httpSession.isNew()) {
            return new ModelAndView("login");
        }
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));

        if (utilisateur == null) {
            return new ModelAndView("/login");
        }

        if (listIdUsers != null) {
            StringBuilder mailString = new StringBuilder();

            int j = 0;
            for (int i : listIdUsers) {
                Utilisateur user = (Utilisateur) sessionHibernate.get(Utilisateur.class, i);
                if (j == listIdUsers.length - 1) {
                    mailString = mailString.append(user.getEmail());
                } else {
                    mailString = mailString.append(user.getEmail() + ",");
                }
                j++;
            }

            modelAndView.addObject("mailString", mailString);
        }

        String signatureMail = "\n\n\n\n\n\n----\nEnvoyé depuis le site Lotus\n" +
                "Expéditeur : " + utilisateur.getPrenom() + " " + utilisateur.getNom() + "\n" +
                "Email de l'expéditeur : " + utilisateur.getEmail();

        modelAndView.addObject("signatureMail", signatureMail);

        modelAndView.addObject("listAnneeScolaire", sessionHibernate.createQuery("select s from annee_scolaire s").list());
        modelAndView.addObject("listActivite", sessionHibernate.createQuery("select a from activite a").list());
        modelAndView.addObject("listParcours", sessionHibernate.createQuery("select p from parcours p").list());

        sessionHibernate.close();
        modelAndView.addObject(utilisateur);
        modelAndView.setViewName("/messaging");
        return modelAndView;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public ModelAndView doSendEmail(HttpServletRequest request, @RequestParam("email") String[] adress, @RequestParam("objmessage") String subject, @RequestParam("message") String message, HttpSession httpSession,
                                    ModelAndView modelAndView) {
        // takes input from e-mail form

        for(int i = 0; i < adress.length; i++)
        {   // prints debug info
//            System.out.println("To: " + adress[i]);

            // creates a simple e-mail object
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(adress[i]);
            email.setSubject(subject);
            email.setText(message);
            // sends the e-mail
            mailSender.send(email);
        }

        // prints debug info
//        System.out.println("Subject: " + subject);
//        System.out.println("Message: " + message);

        // forwards to the view named "Result"
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        modelAndView.addObject(utilisateur);
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

}
