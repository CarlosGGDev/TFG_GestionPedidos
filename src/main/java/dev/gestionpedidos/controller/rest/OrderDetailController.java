package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.DTO.OrderDetailDTO;
import dev.gestionpedidos.model.OrderDetail;
import dev.gestionpedidos.service.OrderDetailService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {

	OrderDetailService orderDetailService;

	public OrderDetailController(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	// TOREV: utiliza @RequestParam (parametro en la URL)
	@GetMapping(value = "/pedidos/detalle",
				params = "id_pedido") // http://localhost:8080/pedidos/detalle?id_pedido=1
	public ResponseEntity<List<OrderDetail>> getOrderDetails(@RequestParam("id_pedido") int orderId) {
		Optional<List<OrderDetail>> orderDetailsOpt = orderDetailService.getOrderDetails(orderId);
		return ResponseEntity.of(orderDetailsOpt);
	}

	// TOREV: utiliza @RequestParam (parametro en la URL)
	@GetMapping(value = "/pedidos/detalle",
				params = {"id_pedido", "id_detalle"}) // http://localhost:8080/pedidos/detalle?id_pedido=1&id_detalle=1
	public ResponseEntity<OrderDetail> getOrderDetail(@RequestParam("id_pedido") int orderId, @RequestParam("id_detalle") int orderDetailId) {
		Optional<OrderDetail> orderDetailOpt = orderDetailService.getOrderDetail(orderId, orderDetailId);
		return ResponseEntity.of(orderDetailOpt);
	}

	@PostMapping(value = "/pedidos/detalle")// http://localhost:8080/pedidos/detalle
	public void saveOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO, HttpSession session) {
		int orderId = (int) session.getAttribute("orderId");
		orderDetailDTO.setOrder_id(orderId);
		orderDetailService.saveOrderDetail(orderDetailDTO);
	}

	@DeleteMapping(value = "/pedidos/detalle/{orderId}") // http://localhost:8080/pedidos/detalle/1
	public void deleteOrderDetails(@PathVariable("orderId") int orderId) {
		orderDetailService.deleteOrderDetails(orderId);
	}

	@DeleteMapping(value = "/pedidos/detalle/{orderId}/{orderDetailId}") // http://localhost:8080/pedidos/detalle/1/1
	public ResponseEntity<OrderDetail> deleteOrderDetail(@PathVariable("orderId") int orderId, @PathVariable("orderDetailId") int orderDetailId) {
		Optional<OrderDetail> orderDetailOpt = orderDetailService.deleteOrderDetail(orderId, orderDetailId);
		return ResponseEntity.of(orderDetailOpt);
	}
}
