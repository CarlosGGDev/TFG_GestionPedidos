package dev.gestionpedidos.service;

import dev.gestionpedidos.model.OrderDetail;
import dev.gestionpedidos.repository.OrderDetailRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	OrderDetailRepository orderDetailRepository;

	// CONSTRUCTOR

	public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
		this.orderDetailRepository = orderDetailRepository;
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
	public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
		return this.orderDetailRepository.save(orderDetail);
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
