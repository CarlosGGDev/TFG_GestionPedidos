package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.model.Order;
import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.OrderService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(value = "/pedidos") // http://localhost:8080/pedidos
	public ResponseEntity<List<Order>> getOrders() {
		return ResponseEntity.ok(orderService.getOrders());
	}

	// TOREV: utiliza @RequestParam (parametro en la URL)
	@GetMapping(value = "/pedidos",
				params = "id_pedido") // http://localhost:8080/pedidos?id_pedido=1
	public ResponseEntity<Order> getOrder(@RequestParam("id_pedido") int orderId) {
		Optional<Order> orderOpt = orderService.getOrder(orderId);
		return ResponseEntity.of(orderOpt);
	}

	// TOREV: utiliza @RequestParam (parametro en la URL)
	@GetMapping(value = "/pedidos",
				params = "id_usuario") // http://localhost:8080/pedidos?id_usuario=1
	public ResponseEntity<List<Order>> getCustomerOrders(@RequestParam("id_usuario") int userId) {
		Optional<List<Order>> ordersOpt = orderService.getCustomerOrders(userId);
		return ResponseEntity.of(ordersOpt);
	}

	@PostMapping(value = "/pedidos") // http://localhost:8080/pedidos
	public ResponseEntity<Order> saveOrder(@RequestBody Order order, HttpSession session) {
		User user = (User) session.getAttribute("user");
		order.setUser(user);
		if (order.getShippingAddress() == null) {
			order.setShippingAddress(user.getAddress());
		}
		Order savedOrder = orderService.saveOrder(order);
		session.setAttribute("orderId", savedOrder.getId());
		return ResponseEntity.ok(savedOrder);
	}

	// TOREV: poner en documentacion que estos dos metodos se llaman en funcion de los parametros de la request.
	//  Los dos son de tipo POST pero segun los parametros entrara por uno u otro, y devuelve una redireccion
	@PostMapping(value = "/pedidos/editar",
				 params = {"orderId", "status"}) // http://localhost:8080/pedidos/editar
	public void editOrderStatus(HttpServletResponse response,
								@RequestParam(value = "orderId") int orderId,
								@RequestParam(value = "status") String status) throws IOException {
		this.orderService.editOrderStatus(orderId, status);
		response.sendRedirect("/");
	}

	@PostMapping(value = "/pedidos/editar",
				 params = {"orderId", "comment"}) // http://localhost:8080/pedidos/editar
	public void editOrderComment(HttpServletResponse response,
								@RequestParam(value = "orderId") int orderId,
								@RequestParam(value = "comment") String comment) throws IOException {
		this.orderService.editOrderComment(orderId, comment);
		response.sendRedirect("/");
	}

	@DeleteMapping(value = "/pedidos/{orderId}") // http://localhost:8080/pedidos/1
	public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") int orderId) {
		Optional<Order> orderOpt = orderService.deleteOrder(orderId);
		return ResponseEntity.of(orderOpt);
	}
}
