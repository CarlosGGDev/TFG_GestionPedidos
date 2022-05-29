package dev.gestionpedidos.service;

import dev.gestionpedidos.dto.OrderDetailDto;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Order Detail service implementation.
 * The Jdbc Template and Data Source are injected as dependencies at controller level.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	private final JdbcTemplate jdbcTemplate;
	private final DataSource dataSource;

	public OrderDetailServiceImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		this.jdbcTemplate = jdbcTemplate;
		this.dataSource = dataSource;
		jdbcTemplate.setDataSource(dataSource);
	}

	/**
	 * Saves an order detail.
	 * Get a plain object order detail dto and persist data with JDBC.
	 * @param orderDetailDTO
	 */
	@Override
	@Transactional
	public void saveOrderDetail(OrderDetailDto orderDetailDTO) {
		int orderId = orderDetailDTO.getOrder_id();
		int productId = orderDetailDTO.getProduct_id();
		int quantity = orderDetailDTO.getQuantity();
		double price = orderDetailDTO.getPrice();
		double total = orderDetailDTO.getTotal();
		this.jdbcTemplate.update("INSERT INTO orders_details (order_id, product_id, quantity, price, total) VALUES (" + orderId + "," +
																													    productId + "," +
																													    quantity + "," +
																														price + "," +
																														total + ");");
	}
}
