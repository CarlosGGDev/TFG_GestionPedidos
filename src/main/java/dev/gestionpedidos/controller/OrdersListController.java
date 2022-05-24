package dev.gestionpedidos.controller;

import dev.gestionpedidos.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pedidos/historial")
public class OrdersListController {

	private final OrderService orderService;

	public OrdersListController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping // http://localhost:8080/pedidos
	public String showOrdersList(Model model) {
		model.addAttribute("orders", this.orderService.getOrders());
		return "orders";
	}
}
