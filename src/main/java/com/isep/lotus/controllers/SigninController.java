package com.isep.lotus.controllers;

import com.isep.lotus.models.Utilisateur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.isep.lotus.LotusApplication.getSession;
import static org.unbescape.html.HtmlEscape.escapeHtml4;

@Controller
public class SigninController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView signinDisplay() {return new ModelAndView("/signin");}

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ModelAndView signinProcess(@RequestParam("prenom") String firstname,
                                      @RequestParam("nom") String name,
                                      @RequestParam("email") String mail,
                                      @RequestParam("identifiant") String pseudo,
                                      @RequestParam("mdp") String password,
                                      @RequestParam("conf-mdp") String confPassword,
                                      ModelAndView modelAndView) {
        String erreur = "Erreur : ";
        String prenom = firstNameProcess(firstname);
        String nom = nameProcess(name);
        String email = secureFieldString(mail);
        String identifiant = secureFieldString(pseudo);
        String mdp = passWordEncryption(password);
        String confMdp = passWordEncryption(confPassword);

        if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || identifiant.isEmpty() || mdp.isEmpty() || confMdp.isEmpty()) {
            erreur = erreur + "veuillez remplir tous les champs marqués d'une astérisque";
            modelAndView.addObject("lastPrenom", prenom);
            modelAndView.addObject("lastNom", nom);
            modelAndView.addObject("lastEmail", email);
            modelAndView.addObject("lastIdentifiant", identifiant);
            modelAndView.addObject("erreur", erreur).setViewName("signin");
            return modelAndView;
        } else {
            Session sessionHibernate1 = getSession();
            Utilisateur utilisateur1 = (Utilisateur) sessionHibernate1.createQuery("select u " +
                    "from utilisateur u " +
                    "where u.identifiant like :identifiant")
                    .setParameter("identifiant", identifiant)
                    .uniqueResult();
            sessionHibernate1.close();

            if (utilisateur1 != null) {
                erreur = erreur + "cet identifiant est déjà utilisé";
                modelAndView.addObject("lastPrenom", prenom);
                modelAndView.addObject("lastNom", nom);
                modelAndView.addObject("lastEmail", email);
                modelAndView.addObject("lastIdentifiant", identifiant);
                modelAndView.addObject("erreur", erreur).setViewName("signin");
                return modelAndView;
            } else {
                Session sessionHibernate2 = getSession();
                Utilisateur utilisateur2 = (Utilisateur) sessionHibernate2.createQuery("select u " +
                        "from utilisateur u " +
                        "where u.email like :email")
                        .setParameter("email", email)
                        .uniqueResult();
                sessionHibernate2.close();

                if (utilisateur2 != null) {
                    erreur = erreur + "cet email est déjà associé à un compte";
                    modelAndView.addObject("lastPrenom", prenom);
                    modelAndView.addObject("lastNom", nom);
                    modelAndView.addObject("lastEmail", email);
                    modelAndView.addObject("lastIdentifiant", identifiant);
                    modelAndView.addObject("erreur", erreur).setViewName("signin");
                    return modelAndView;
                } else {

                    if (!mdp.equals(confMdp)) {
                        erreur = erreur + "le mot de passe et sa confirmation ne correspondent pas";
                        modelAndView.addObject("lastPrenom", prenom);
                        modelAndView.addObject("lastNom", nom);
                        modelAndView.addObject("lastEmail", email);
                        modelAndView.addObject("lastIdentifiant", identifiant);
                        modelAndView.addObject("erreur", erreur).setViewName("signin");
                        return modelAndView;
                    } else {
                        Utilisateur utilisateur = new Utilisateur(identifiant, mdp, prenom, nom, email);

                        // TODO : gérer l'ajout de la photo

                        Session sessionHibernate3 = getSession();
                        Transaction tx = sessionHibernate3.beginTransaction();
                        sessionHibernate3.persist(utilisateur);
                        tx.commit();
                        sessionHibernate3.close();
                        modelAndView.setViewName("/login");
                        return modelAndView;

                    }
                }
            }
        }
    }

    public String secureFieldString (String inputString) {
        return inputString.trim();
    }

    public String nameProcess (String inputString) {
        return inputString.toUpperCase();
    }

    public String firstNameProcess (String inputString) {
        if (inputString.isEmpty()) {
            return inputString;
        }
        char[] char_table = inputString.toLowerCase().toCharArray();
        char_table[0] = Character.toUpperCase(char_table[0]);
        return new String(char_table);
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
