package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
	Optional<Order> getOrder(int orderId);
	List<Order> getOrders();
	Optional<List<Order>> getPendingOrders();
	Optional<List<Order>> getDeliveredOrders();

	// TODO: pedidos cancelados? Se borran o se ponen como cancelado

	Optional<List<Order>> getCustomerOrders(int userId);
	Optional<List<Order>> getCustomerPendingOrders(int userId);
	Optional<List<Order>> getCustomerDeliveredOrders(int userId);
	Order saveOrder(Order order);
	Optional<Order> deleteOrder(int orderId);
}
