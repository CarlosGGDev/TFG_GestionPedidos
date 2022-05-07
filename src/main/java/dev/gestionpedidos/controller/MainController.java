package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.OrderService;
import dev.gestionpedidos.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MainController {

	private UserService userService;
	private OrderService orderService;

	public MainController(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}

	@GetMapping
	public String showMain(@AuthenticationPrincipal UserDetails userDetails, HttpSession session, Model model) {
		// Si no hay un usuario guardado en la sesion, lo guarda (solo cuando se inicia sesion por primera vez).
		// Si hay un usuario guardado, no lo vuelve a guardar, de esta forma cuando se actualizan los datos del perfil y se guarda
		// el nuevo usuario en la sesion, puede recuperar los datos del usuario, si no da error, ya que intenta recuperar los datos del usuario
		// que habia iniciado sesion, pero al cambiar los datos del perfil da error.
		User user;
		if (session.getAttribute("user") == null) {
			user = userService.findByName(userDetails.getUsername());
		} else {
			user = (User) session.getAttribute("user");
		}
		session.setAttribute("user", user);
		model.addAttribute("pendingOrders", orderService.getCustomerPendingOrders(user.getId()).get());
		model.addAttribute("previousOrders", orderService.getCustomerPreviousOrders(user.getId()).get());
		return user.getRole().name() == "ROLE_ADMIN" ? "mainAdmin" : "main";
	}

}
