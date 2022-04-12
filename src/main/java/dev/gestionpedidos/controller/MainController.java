package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MainController {

	private UserService userService;

	public MainController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String showMain(Authentication authentication, HttpSession httpSession) {
		// TOREV: esta parte de codigo es para mostrar el nombre de usuario que esta iniciado (refactorizar)
		// TOREV: ESTA PARTE DEBERIA IR EN EL CONTROLADOR DE LOGIN, PERO NO PASA POR EL CONTROLADOR TRAS INICIAR SESION...
		String name = authentication.getName();
		if (httpSession.getAttribute("user") == null) {
			User user = this.userService.findByName(name);
			user.setPassword(null);
			httpSession.setAttribute("user", user);
		}
		return "main";
	}

}
