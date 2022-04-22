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
		User user = userService.findByName(userDetails.getUsername());
		session.setAttribute("user", user);
		model.addAttribute("pendingOrders", orderService.getCustomerPendingOrders(user.getId()).get());
		model.addAttribute("previousOrders", orderService.getCustomerPreviousOrders(user.getId()).get());
		return "main";
	}

}
