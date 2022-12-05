package mk.ukim.finki.labwp.repository.IMPL;

import mk.ukim.finki.labwp.bootstrap.DataHolder;
import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Order;
import mk.ukim.finki.labwp.model.enumerations.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    public List<Order> findAllOrders(){

        return DataHolder.orders;
    }

    public Optional<Order> findById(Long id) {
        return DataHolder.orders.stream().filter(i -> i.getOrderId().equals(id)).findFirst();
    }
    public Optional<Order> findByUsername(String clientName) {
        return DataHolder.orders.stream().filter(r->r.getUser().getUsername().equals(clientName)).findFirst();
    }


    public Optional<Order> findByNameAndStatus(String clientName, OrderStatus status) {
        return DataHolder.orders.stream()
                .filter(i -> i.getUser().getUsername().equals(clientName) && i.getStatus().equals(status))
                .findFirst();
    }

    public Order save(Order order) {
        DataHolder.orders
                .removeIf(i -> i.getUser().getUsername().equals(order.getUser().getUsername()));
        DataHolder.orders.add(order);
        return order;
    }

}
