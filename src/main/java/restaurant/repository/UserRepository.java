package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import restaurant.model.User;

public interface UserRepository extends JpaRepository<User, Long> ,CrudRepository<User, Long> {
    User findByUsername(String username);
}
