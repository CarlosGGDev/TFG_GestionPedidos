package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Order;
import dev.gestionpedidos.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Order service implementation.
 * The Order repository is injected as dependency at controller level.
 * Methods invoke the repository to persist data.
 */
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	/**
	 * Get an order by id
	 * @param orderId Order id
	 * @return Optional of order
	 */
	@Override
	public Optional<Order> getOrder(int orderId) {
		return this.orderRepository.findById(orderId);
	}

	/**
	 * Get all orders
	 * @return Optional of orders list
	 */
	@Override
	public Optional<List<Order>> getOrders() {
		return Optional.of(this.orderRepository.findAll());
	}

	/**
	 * Get all orders with status 'pendiente'
	 * @return Optional of orders list
	 */
	@Override
	public Optional<List<Order>> getPendingOrders() {
		return this.orderRepository.getPendingOrders();
	}

	/**
	 * Get all orders with status 'enviado'
	 * @return Optional of orders list
	 */
	@Override
	public Optional<List<Order>> getSentOrders() {
		return this.orderRepository.getSentOrders();
	}

	/**
	 * Get all orders of a customer with status 'pendiente' or 'enviado'
	 * @param userId User id
	 * @return Optional of orders list
	 */
	@Override
	public Optional<List<Order>> getCustomerPendingOrders(int userId) {
		return this.orderRepository.getCustomerPendingOrders(userId);
	}

	/**
	 * Get all orders of a customer with status 'entregado'
	 * @param userId User id
	 * @return Optional of orders list
	 */
	@Override
	public Optional<List<Order>> getCustomerDeliveredOrders(int userId) {
		return this.orderRepository.getCustomerDeliveredOrders(userId);
	}

	/**
	 * Saves an order
	 * @param order Order to be saved
	 * @return Optional of saved order
	 */
	@Override
	public Optional<Order> saveOrder(Order order) {
		return Optional.of(this.orderRepository.save(order));
	}

	/**
	 * Edits the status of an order
	 * @param orderId The id of the order to be edited
	 * @param status The new order status to be saved
	 */
	@Override
	public void editOrderStatus(int orderId, String status) {
		this.orderRepository.editOrderStatus(orderId, status);
	}

	/**
	 * Edits the comment of an order
	 * @param orderId The id of the order to be edited
	 * @param comment The new order comment to be saved
	 */
	@Override
	public void editOrderComment(int orderId, String comment) {
		this.orderRepository.editOrderComment(orderId, comment);
	}

	/**
	 * Deletes an order
	 * @param orderId Id of the order to be deleted
	 */
	@Override
	public void deleteOrder(int orderId) {
		Optional<Order> orderOpt = this.getOrder(orderId);
		if (orderOpt.isPresent()) {
			this.orderRepository.deleteById(orderId);
		}
	}
}
