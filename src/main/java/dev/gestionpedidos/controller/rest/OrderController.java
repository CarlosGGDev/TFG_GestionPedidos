package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.model.Order;
import dev.gestionpedidos.model.User;
import dev.gestionpedidos.service.OrderService;
import java.io.IOException;
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

	// TODO: docu - este metodo al devovler ResponseEntity.ok desde javascript se puede hacer la funcion en caso de success
	@PostMapping(value = "/pedidos") // http://localhost:8080/pedidos
	public ResponseEntity<Order> saveOrder(@RequestBody Order order, HttpSession session) {
		User user = (User) session.getAttribute("user");
		order.setUser(user);
		if (order.getShippingAddress() == null) {
			order.setShippingAddress(user.getAddress());
		}
		Optional<Order> savedOrderOpt = orderService.saveOrder(order);
		if (savedOrderOpt.isPresent()) {
			session.setAttribute("orderId", savedOrderOpt.get().getId());
		}
		return ResponseEntity.of(savedOrderOpt);
	}

	// TODO: poner en documentacion que estos dos metodos se llaman en funcion de los parametros de la request.
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
	public void deleteOrder(@PathVariable("orderId") int orderId) {
		orderService.deleteOrder(orderId);
	}
}
