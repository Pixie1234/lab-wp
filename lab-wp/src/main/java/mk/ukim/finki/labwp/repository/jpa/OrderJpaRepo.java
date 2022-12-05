package mk.ukim.finki.labwp.repository.jpa;

import mk.ukim.finki.labwp.model.Order;
import mk.ukim.finki.labwp.model.User;
import mk.ukim.finki.labwp.model.enumerations.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderJpaRepo extends JpaRepository<Order,Long> {
    Optional<Order> findByUserAndStatus(User user, OrderStatus status);
    Optional<User> findByUser(String user);
}


