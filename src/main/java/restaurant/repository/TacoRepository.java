package restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import restaurant.model.Order;

public interface TacoRepository
        extends CrudRepository<Order, Long> {

}
