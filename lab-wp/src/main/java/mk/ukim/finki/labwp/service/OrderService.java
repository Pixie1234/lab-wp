package mk.ukim.finki.labwp.service;

import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(String balloonColor,String balloonSize, String clientName, String address,Long orderId);

    List<Order>listOrders();
    List<Balloon> listAllBalloonsInOrder(Long orderId);
    Order getActiveOrder(String  clientName);

    Order addBalloonToOrder(String clientName, Long productId);


}

