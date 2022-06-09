package dev.gestionpedidos.controller.rest;

import dev.gestionpedidos.dto.OrderDetailDto;
import dev.gestionpedidos.service.OrderDetailService;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller to manage request of orders details
 */
@RestController
public class OrderDetailController {

	private final OrderDetailService orderDetailService;

	public OrderDetailController(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	/**
	 * POST controller for save an order detail. When this URL receives a request, the service saves an order detail.
	 * This controllers receives a DTO object, not an entity of Order Detail
	 * @param orderDetailDTO - Order detail to be saved
	 * @param session - Http session
	 */
	@PostMapping(value = "/pedidos/detalle")// http://localhost:8080/pedidos/detalle
	public void saveOrderDetail(@RequestBody OrderDetailDto orderDetailDTO, HttpSession session) {
		int orderId = (int) session.getAttribute("orderId");
		orderDetailDTO.setOrder_id(orderId);
		this.orderDetailService.saveOrderDetail(orderDetailDTO);
	}
}
