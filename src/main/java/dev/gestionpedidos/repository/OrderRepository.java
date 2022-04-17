package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order o WHERE o.user.id = ?1")
	Optional<List<Order>> getCustomerOrders(int userId);

	@Query("SELECT o FROM Order o WHERE o.user.id = ?1 AND o.status = 'pendiente' OR o.status = 'enviado'")
	Optional<List<Order>> getCustomerPendingOrders(int userId);

	@Query("SELECT o FROM Order o WHERE o.user.id = ?1 AND o.status = 'entregado'")
	Optional<List<Order>> getCustomerPreviousOrders(int userId);
}
