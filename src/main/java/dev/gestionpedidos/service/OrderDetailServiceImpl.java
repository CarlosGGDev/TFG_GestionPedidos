package dev.gestionpedidos.service;

import dev.gestionpedidos.DTO.OrderDetailDTO;
import dev.gestionpedidos.model.OrderDetail;
import dev.gestionpedidos.repository.OrderDetailRepository;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	private final OrderDetailRepository orderDetailRepository;
	private final JdbcTemplate jdbcTemplate;
	private final DataSource dataSource;


	// CONSTRUCTOR

	public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, JdbcTemplate jdbcTemplate, DataSource dataSource) {
		this.orderDetailRepository = orderDetailRepository;
		this.jdbcTemplate = jdbcTemplate;
		this.dataSource = dataSource;
		jdbcTemplate.setDataSource(dataSource);
	}

	// CRUD METHODS

	@Override
	public Optional<List<OrderDetail>> getOrderDetails(int orderId) {
		return this.orderDetailRepository.getOrderDetails(orderId);
	}

	@Override
	public Optional<OrderDetail> getOrderDetail(int orderId, int orderDetailId) {
		return this.orderDetailRepository.getOrderDetail(orderId, orderDetailId);
	}

	@Override
	@Transactional
	public void saveOrderDetail(OrderDetailDTO orderDetailDTO) {
		int orderId = orderDetailDTO.getOrder_id();
		int productId = orderDetailDTO.getProduct_id();
		int quantity = orderDetailDTO.getQuantity();
		double price = orderDetailDTO.getPrice();
		double total = orderDetailDTO.getTotal();
		jdbcTemplate.update("INSERT INTO orders_details (order_id, product_id, quantity, price, total) VALUES (" + orderId + "," +
																													    productId + "," +
																													    quantity + "," +
																														price + "," +
																														total + ");");
	}

	@Override
	public void deleteOrderDetails(int orderId) {
		orderDetailRepository.deleteOrderDetails(orderId);
	}

	// TODO: necesario??
	@Override
	public Optional<OrderDetail> deleteOrderDetail(int orderId, int orderDetailId) {
		Optional<OrderDetail> orderDetailOpt = getOrderDetail(orderId, orderDetailId);
		if (orderDetailOpt.isPresent()) {
			orderDetailRepository.deleteById(orderDetailId);
		}
		return orderDetailOpt;
	}

}
