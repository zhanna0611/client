package restaurant.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}