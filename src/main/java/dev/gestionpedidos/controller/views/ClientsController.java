package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML file with clients information. Only available for admin user
 */
@Controller
public class ClientsController {

	private final UserService userService;

	public ClientsController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Controller that returns a view. The users information is sent with a Model object
	 * @param model Object to send data to the view
	 * @return HTML file
	 */
	@GetMapping(value = "/admin/clientes") // http://localhost:8080/admin/clientes
	public String showClientsList(Model model) {
		model.addAttribute("clients", this.userService.getUsers().get());
		return "admin/clientsAdmin";
	}
}
