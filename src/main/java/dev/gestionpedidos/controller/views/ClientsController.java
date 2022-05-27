package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientsController {

	private final UserService userService;

	public ClientsController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/admin/clientes") // http://localhost:8080/admin/clientes
	public String showClientsList(Model model) {
		model.addAttribute("clients", this.userService.getUsers().get());
		return "admin/clientsAdmin";
	}
}
