package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML file with sign in form
 */
@Controller
public class SigninController {

    /**
     * Controller that returns a view with log in form.
     * Send a User object and binds it to the form
     * @param model Object to send data to the view
     * @return HTML file
     */
    @GetMapping(value = "/acceso") // http://localhost:8080/acceso
    public String showSignInForm(Model model) {
        model.addAttribute("user", new User());
        return "public/signin";
    }
}