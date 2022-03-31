package dev.gestionpedidos.repository;

import dev.gestionpedidos.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o from Order o WHERE o.user.id = ?1")
	Optional<List<Order>> getCustomerOrders(int userId);
}
