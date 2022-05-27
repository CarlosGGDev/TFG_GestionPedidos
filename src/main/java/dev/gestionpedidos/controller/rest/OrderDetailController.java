package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.DTO.OrderDetailDTO;
import dev.gestionpedidos.service.OrderDetailService;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {

	private final OrderDetailService orderDetailService;

	public OrderDetailController(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	@PostMapping(value = "/pedidos/detalle")// http://localhost:8080/pedidos/detalle
	public void saveOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO, HttpSession session) {
		int orderId = (int) session.getAttribute("orderId");
		orderDetailDTO.setOrder_id(orderId);
		orderDetailService.saveOrderDetail(orderDetailDTO);
	}
}
