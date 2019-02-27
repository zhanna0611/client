package restaurant.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import restaurant.controller.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}