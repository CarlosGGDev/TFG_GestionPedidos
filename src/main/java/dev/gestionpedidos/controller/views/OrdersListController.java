package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrdersListController {

	private final OrderService orderService;

	public OrdersListController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(value = "/admin/pedidos/historial") // http://localhost:8080/admin/pedidos/historial
	public String showOrdersList(Model model) {
		model.addAttribute("orders", this.orderService.getOrders());
		return "admin/orders";
	}
}
