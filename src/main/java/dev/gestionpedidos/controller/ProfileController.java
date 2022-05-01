package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/perfil")
public class ProfileController {

	private final UserService userService;

	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String showProfile() {
		return "profile";
	}

	@PostMapping // http://localhost:8080/registro
	public String updateProfile(@Valid @ModelAttribute User user, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		user.setId(sessionUser.getId());
		userService.saveUser(user);
		// Es necesario volver a guardar el usuario en la sesion, ya que ahora el usuario ha cambiado
		session.setAttribute("user", user);
		return "redirect:/perfil";
	}

}
