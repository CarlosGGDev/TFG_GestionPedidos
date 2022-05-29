package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML file with sign up form
 */
@Controller
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Controller that returns a view with sign up form.
     * Send a User object and binds it to the form
     * @param model Object to send data to the view
     * @return HTML file
     */
    @GetMapping(value = "/registro") // http://localhost:8080/registro
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "public/signup";
    }

    /**
     * POST controller to control user registration.
     * To check that the new user information doesnt'exist in database, it does
     * a search for each value. It allow us to show a specific message for each
     * field, and know what value exists.
     *
     * If the registrarion has errors, they are displayed in each field.
     * If the registration is correct, the user is redirected to a success page.
     *
     * @param user User to be saved
     * @param result Object with possible errors
     * @param model Object to bind data between controller and view
     * @return HTML file
     */
    @PostMapping(value = "/registro") // http://localhost:8080/registro
    public String signUp(@Valid @ModelAttribute User user,
                         BindingResult result,
                         Model model) {
        Optional<User> nameEntry = this.userService.findByName(user.getName());
        Optional<User> emailEntry = this.userService.findByEmail(user.getEmail());
        Optional<User> nifEntry = this.userService.findByNif(user.getNif());

        if (result.hasErrors()) {
            return "redirect:/registro";
        }
        if (nameEntry.isPresent()) {
            model.addAttribute("nameError", "El nombre ya existe");
        }
        if (emailEntry.isPresent()) {
            model.addAttribute("emailError", "El email ya existe");
        }
        if (nifEntry.isPresent()) {
            model.addAttribute("nifError", "El NIF ya existe");
        }
        if (nameEntry.isEmpty() && emailEntry.isEmpty() && nifEntry.isEmpty()){
            this.userService.saveUser(user);
            return "public/signupSuccess";
        }
        return "public/signup";
    }
}