package dev.gestionpedidos.controller;

import dev.gestionpedidos.model.OrderDetail;
import dev.gestionpedidos.model.Product;
import dev.gestionpedidos.service.OrderDetailService;
import java.util.List;
import java.util.Optional;
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
@RequestMapping(value = "/pedidos/detalle")
public class OrderDetailController {

	OrderDetailService orderDetailService;

	public OrderDetailController(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	// ATENCION: utiliza @RequestParam (parametro en la URL)
	@GetMapping(params = "id_pedido") // http://localhost:8080/pedidos/detalle?id_pedido=1
	public ResponseEntity<List<OrderDetail>> getOrderDetails(@RequestParam("id_pedido") int orderId) {
		return ResponseEntity.of(orderDetailService.getOrderDetails(orderId));
	}

	// ATENCION: utiliza @RequestParam (parametro en la URL)
	@GetMapping(params = {"id_pedido", "id_detalle"}) // http://localhost:8080/pedidos/detalle?id_pedido=1&id_detalle=1
	public ResponseEntity<OrderDetail> getOrderDetail(@RequestParam("id_pedido") int orderId, @RequestParam("id_detalle") int orderDetailId) {
		Optional<OrderDetail> orderDetailOpt = orderDetailService.getOrderDetail(orderId, orderDetailId);
		return ResponseEntity.of(orderDetailOpt);
	}

	@PostMapping// http://localhost:8080/pedidos/detalle
	public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail) {
		return ResponseEntity.ok(orderDetailService.saveOrderDetail(orderDetail));
	}

	@DeleteMapping(value = "/{orderId}/{orderDetailId}") // http://localhost:8080/pedidos/detalle/1/1
	public ResponseEntity<OrderDetail> deleteOrderDetail(@PathVariable("orderId") int orderId, @PathVariable("orderDetailId") int orderDetailId) {
		Optional<OrderDetail> orderDetailOpt = orderDetailService.deleteOrderDetail(orderId, orderDetailId);
		return ResponseEntity.of(orderDetailOpt);
	}
}
