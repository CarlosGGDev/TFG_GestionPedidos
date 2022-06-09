package dev.gestionpedidos.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML file with logout message
 */
@Controller
public class LogoutController {

	/**
	 * Controller that returns a view. When the user logs out, is redirected to this view
	 */
	@GetMapping(value = "/logout") // http://localhost:8080/logout
	public String logout() {
		return "public/logout";
	}

}
