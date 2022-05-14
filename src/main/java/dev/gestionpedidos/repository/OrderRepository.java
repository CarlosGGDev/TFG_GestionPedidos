package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order o WHERE o.status LIKE 'pendiente'")
	Optional<List<Order>> getPendingOrders();

	@Query("SELECT o FROM Order o WHERE o.status LIKE 'enviado'")
	Optional<List<Order>> getSentOrders();

	@Query("SELECT o FROM Order o WHERE o.status = 'entregado'")
	Optional<List<Order>> getDeliveredOrders();

	@Query("SELECT o FROM Order o WHERE o.user.id = ?1")
	Optional<List<Order>> getCustomerOrders(int userId);

	@Query("SELECT o FROM Order o WHERE o.user.id = ?1 AND o.status IN ('pendiente','enviado')")
	Optional<List<Order>> getCustomerPendingOrders(int userId);

	@Query("SELECT o FROM Order o WHERE o.user.id = ?1 AND o.status = 'entregado'")
	Optional<List<Order>> getCustomerDeliveredOrders(int userId);

	@Modifying
	@Transactional
	@Query("UPDATE Order o SET o.status = :status WHERE o.id = :orderId")
	void editOrderStatus(int orderId, String status);

	@Modifying
	@Transactional
	@Query("UPDATE Order o SET o.comment = :comment WHERE o.id = :orderId")
	void editOrderComment(int orderId, String comment);
}
