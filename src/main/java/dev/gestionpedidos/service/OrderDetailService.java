package dev.gestionpedidos.service;

import dev.gestionpedidos.model.OrderDetail;
import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
	Optional<List<OrderDetail>> getOrderDetails(int orderId);
	Optional<OrderDetail> getOrderDetail(int orderId, int orderDetailId);
	OrderDetail saveOrderDetail(OrderDetail orderDetail);
	Optional<List<OrderDetail>> deleteOrderDetails(int orderId);
	Optional<OrderDetail> deleteOrderDetail(int orderId, int orderDetailId);
}
