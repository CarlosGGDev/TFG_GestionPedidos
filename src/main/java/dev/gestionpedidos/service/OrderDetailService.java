package dev.gestionpedidos.service;

import dev.gestionpedidos.DTO.OrderDetailDTO;
import dev.gestionpedidos.model.OrderDetail;
import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
	Optional<List<OrderDetail>> getOrderDetails(int orderId);
	Optional<OrderDetail> getOrderDetail(int orderId, int orderDetailId);
	void saveOrderDetail(OrderDetailDTO orderDetailDTO);
	void deleteOrderDetails(int orderId);
	Optional<OrderDetail> deleteOrderDetail(int orderId, int orderDetailId);
}
