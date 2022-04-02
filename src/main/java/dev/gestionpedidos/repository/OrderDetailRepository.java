package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.OrderDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	@Query("SELECT o FROM OrderDetail o WHERE o.order.id = ?1")
	Optional<List<OrderDetail>> getOrderDetails(int orderId);

	@Query("SELECT o FROM OrderDetail o WHERE o.order.id = ?1 AND o.id = ?2")
	Optional<OrderDetail> getOrderDetail(int orderId, int orderDetailId);

	@Query("DELETE FROM OrderDetail o WHERE o.order.id = ?1")
	Optional<List<OrderDetail>> deleteOrderDetails(int orderId);

	@Query("DELETE FROM OrderDetail o WHERE o.order.id = ?1 AND o.id = ?2")
	Optional<OrderDetail> deleteOrderDetail(int orderId, int orderDetailId);
}
