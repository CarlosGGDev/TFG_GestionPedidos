package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Order;
import dev.gestionpedidos.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Optional<Order> getOrder(int orderId) {
		return this.orderRepository.findById(orderId);
	}

	@Override
	public Optional<List<Order>> getOrders() {
		return Optional.of(this.orderRepository.findAll());
	}

	@Override
	public Optional<List<Order>> getPendingOrders() {
		return this.orderRepository.getPendingOrders();
	}

	@Override
	public Optional<List<Order>> getSentOrders() {
		return this.orderRepository.getSentOrders();
	}

	@Override
	public Optional<List<Order>> getCustomerPendingOrders(int userId) {
		return this.orderRepository.getCustomerPendingOrders(userId);
	}

	@Override
	public Optional<List<Order>> getCustomerDeliveredOrders(int userId) {
		return this.orderRepository.getCustomerDeliveredOrders(userId);
	}

	@Override
	public Optional<Order> saveOrder(Order order) {
		return Optional.of(this.orderRepository.save(order));
	}

	@Override
	public void editOrderStatus(int orderId, String status) {
		this.orderRepository.editOrderStatus(orderId, status);
	}

	@Override
	public void editOrderComment(int orderId, String comment) {
		this.orderRepository.editOrderComment(orderId, comment);
	}

	@Override
	public void deleteOrder(int orderId) {
		Optional<Order> orderOpt = getOrder(orderId);
		if (orderOpt.isPresent()) {
			this.orderRepository.deleteById(orderId);
		}
	}
}
