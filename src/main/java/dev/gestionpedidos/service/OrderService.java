package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
	Optional<Order> getOrder(int orderId);
	Optional<List<Order>> getOrders();
	Optional<List<Order>> getPendingOrders();
	Optional<List<Order>> getSentOrders();
	Optional<List<Order>> getCustomerPendingOrders(int userId);
	Optional<List<Order>> getCustomerDeliveredOrders(int userId);
	Optional<Order> saveOrder(Order order);
	void editOrderStatus(int orderId, String status);
	void editOrderComment(int orderId, String comment);
	void deleteOrder(int orderId);
}
