package dev.gestionpedidos.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@GetMapping(value = "/logout") // http://localhost:8080/logout
	public String logout() {
		return "public/logout";
	}

}
