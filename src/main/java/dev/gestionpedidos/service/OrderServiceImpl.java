package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Order;
import dev.gestionpedidos.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	// CONSTRUCTORS

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	// CRUD METHODS

	@Override
	public List<Order> getOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public Optional<Order> getOrder(int orderId) {
		return this.orderRepository.findById(orderId);
	}

	@Override
	public Optional<List<Order>> getCustomerOrders(int userId) {
		return this.orderRepository.getCustomerOrders(userId);
	}

	@Override
	public Optional<List<Order>> getCustomerPendingOrders(int userId) {
		return this.orderRepository.getCustomerPendingOrders(userId);
	}

	@Override
	public Optional<List<Order>> getCustomerPreviousOrders(int userId) {
		return this.orderRepository.getCustomerPreviousOrders(userId);
	}

	@Override
	public Order saveOrder(Order order) {
		return this.orderRepository.save(order);
	}

	@Override
	public Optional<Order> deleteOrder(int orderId) {
		Optional<Order> orderOpt = getOrder(orderId);
		if (orderOpt.isPresent()) {
			this.orderRepository.deleteById(orderId);
		}
		return orderOpt;
	}
}
