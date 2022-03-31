package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
	List<Order> getOrders();
	Optional<Order> getOrder(int orderId);
	Optional<List<Order>> getCustomerOrders(int userId);
	Order saveOrder(Order order);
	Optional<Order> deleteOrder(int orderId);
}
