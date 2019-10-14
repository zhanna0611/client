package restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import restaurant.model.Order;
import restaurant.model.User;

import java.util.List;

public interface OrderRepository
        extends CrudRepository<Order, Long> {
//    Page<Order> findAll(Pageable pageable);
    // tag::findByUser_paged[]
//    List<Order> findByIDOrderByPlacedAtDesc(
//            User user, Pageable pageable);
    // end::findByUser_paged[]

  /*
  // tag::findByUser[]
  List<Order> findByUserOrderByPlacedAtDesc(User user);
  // end::findByUser[]
   */

}