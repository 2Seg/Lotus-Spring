package com.isep.lotus.controllers;


import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.isep.lotus.LotusApplication.getSession;
import static org.unbescape.html.HtmlEscape.escapeHtml4;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginDisplay() {return new ModelAndView("login");}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginProcess(@RequestParam("identifiant") String login, @RequestParam("mdp") String password, ModelAndView modelAndView, HttpSession httpSession) {
        String erreur = "Erreur : ";
        String identifiant = secureFieldString(login);
        String mdp = passWordEncryption(password);

        if (identifiant.isEmpty() || mdp.isEmpty()) {
            erreur = erreur + "veuillez remplir tous les champs du formulaire";
            modelAndView.addObject("erreur", erreur).setViewName("login");
            return modelAndView;
        } else {
            Session sessionHibernate = getSession();
            Utilisateur utilisateur = (Utilisateur) sessionHibernate.createQuery("select u " +
                    "from utilisateur u " +
                    "where u.identifiant like :identifiant")
                    .setParameter("identifiant", identifiant)
                    .uniqueResult();
            sessionHibernate.close();

            if (utilisateur == null) {
                erreur = erreur + "identifiant inconnu";
                modelAndView.addObject("erreur", erreur).setViewName("login");
                return modelAndView;
            } else {

                if (!utilisateur.getMdp().equals(mdp)) {
                    erreur = erreur + "mot de passe incorrect";
                    modelAndView.addObject("erreur", erreur).setViewName("login");
                    return modelAndView;
                } else {

                    httpSession.setAttribute("id", utilisateur.getId());
                    httpSession.setAttribute("type", utilisateur.checkUserType());
                    modelAndView.setViewName("redirect:/");
                    return modelAndView;

                }
            }
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.invalidate();
        return new ModelAndView("redirect:/");
    }


    public String secureFieldString (String inputString) {
        return inputString.trim().replaceAll("\\\\", "");
    }

    public String passWordEncryption(String password) {
        if (password.isEmpty()) {
            return password;
        }
        String outputPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            outputPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return outputPassword;
    }

}
