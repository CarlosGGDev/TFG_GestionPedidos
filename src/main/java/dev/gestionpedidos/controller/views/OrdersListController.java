package dev.gestionpedidos.controller.views;

import dev.gestionpedidos.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to manage an HTTP request to return a resource.
 * Return an HTML file with orders information. Only available for admin user
 */
@Controller
public class OrdersListController {

	private final OrderService orderService;

	public OrdersListController(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * Controller that returns a view. The orders information is sent with a Model object
	 * @param model Object to send data to the view
	 * @return HTML file
	 */
	@GetMapping(value = "/admin/pedidos/historial") // http://localhost:8080/admin/pedidos/historial
	public String showOrdersList(Model model) {
		model.addAttribute("orders", this.orderService.getOrders().get());
		return "admin/ordersAdmin";
	}
}
