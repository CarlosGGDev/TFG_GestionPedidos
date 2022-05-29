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

/**
 * REST controller to manage requests of categories
 */
@RestController
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * POST controller for save an order. When this URL receives a request, the service saves an order.
	 * @param order Order to be saved
	 * @param session Http session
	 * @return
	 */
	@PostMapping(value = "/pedidos") // http://localhost:8080/pedidos
	public ResponseEntity<Order> saveOrder(@RequestBody Order order, HttpSession session) {
		User user = (User) session.getAttribute("user");
		order.setUser(user);
		if (order.getShippingAddress() == null) {
			order.setShippingAddress(user.getAddress());
		}
		Optional<Order> savedOrderOpt = this.orderService.saveOrder(order);
		savedOrderOpt.ifPresent(value -> session.setAttribute("orderId", value.getId()));
		return ResponseEntity.of(savedOrderOpt);
	}

	/**
	 * POST controller for edit order status.
	 * When this URL receives a request, the service edits an order status
	 * @param response Http response
	 * @param orderId The id of the order to be edited
	 * @param status New order status to be saved
	 * @throws IOException
	 */
	@PostMapping(value = "/pedidos/editar",
				 params = {"orderId", "status"}) // http://localhost:8080/pedidos/editar
	public void editOrderStatus(HttpServletResponse response,
								@RequestParam(value = "orderId") int orderId,
								@RequestParam(value = "status") String status) throws IOException {
		this.orderService.editOrderStatus(orderId, status);
		response.sendRedirect("/");
	}

	/**
	 * POST controller for edit order comment.
	 * When this URL receives a request, the service edits an order comment.
	 * @param response Http response
	 * @param orderId The id of the order to be edited
	 * @param comment New order comment to be saved
	 * @throws IOException
	 */
	@PostMapping(value = "/pedidos/editar",
				 params = {"orderId", "comment"}) // http://localhost:8080/pedidos/editar
	public void editOrderComment(HttpServletResponse response,
								@RequestParam(value = "orderId") int orderId,
								@RequestParam(value = "comment") String comment) throws IOException {
		this.orderService.editOrderComment(orderId, comment);
		response.sendRedirect("/");
	}

	/**
	 * DELETE controller for delete order. When this URL receives a request, the service deletes an order.
	 * The controller receives the parameters in path
	 * @param orderId The id of the order to be deleted
	 */
	@DeleteMapping(value = "/pedidos/{orderId}") // http://localhost:8080/pedidos/1
	public void deleteOrder(@PathVariable("orderId") int orderId) {
		this.orderService.deleteOrder(orderId);
	}
}
