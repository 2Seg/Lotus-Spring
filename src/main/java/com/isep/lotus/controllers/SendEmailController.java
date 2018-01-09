package com.isep.lotus.controllers;


        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;
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

        import static com.isep.lotus.LotusApplication.getSession;

@Controller

public class SendEmailController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/message")
    public ModelAndView doSendEmail(HttpServletRequest request, @RequestParam("email") String adress,@RequestParam("objmessage") String subject,@RequestParam("message") String message, HttpSession httpSession,
                              ModelAndView modelAndView) {
        // takes input from e-mail form


        // prints debug info
        System.out.println("To: " + adress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);

        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(adress);
        email.setSubject(subject);
        email.setText(message);

        // sends the e-mail
        mailSender.send(email);

        // forwards to the view named "Result"
        Session sessionHibernate = getSession();
        Utilisateur utilisateur = (Utilisateur) sessionHibernate.get(Utilisateur.class, (int) httpSession.getAttribute("id"));
        modelAndView.addObject(utilisateur);
        modelAndView.setViewName("accueil");

        return modelAndView;
    }
}