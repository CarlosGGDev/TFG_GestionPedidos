package dev.gestionpedidos.controller;

import dev.gestionpedidos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/clientes")
public class ClientsController {

	private final UserService userService;

	public ClientsController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String showClientsList(Model model) {
		model.addAttribute("clients", this.userService.getUsers());
		return "clients";
	}
}
