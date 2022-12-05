package mk.ukim.finki.labwp.service.Impl;

import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Order;
import mk.ukim.finki.labwp.model.User;
import mk.ukim.finki.labwp.model.enumerations.OrderStatus;
import mk.ukim.finki.labwp.model.exceptions.BalloonNotFoundException;
import mk.ukim.finki.labwp.model.exceptions.OrderNotFound;
import mk.ukim.finki.labwp.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.labwp.repository.IMPL.BalloonRepository;
import mk.ukim.finki.labwp.repository.IMPL.OrderRepository;
import mk.ukim.finki.labwp.repository.jpa.BalloonJpaRepo;
import mk.ukim.finki.labwp.repository.jpa.OrderJpaRepo;
import mk.ukim.finki.labwp.service.BalloonService;
import mk.ukim.finki.labwp.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service

public class OrderServiceImpl implements OrderService {

    private final OrderJpaRepo orderRepository;
    private final BalloonService balloonService;
    private final BalloonJpaRepo balloonRepository;

    public OrderServiceImpl(OrderJpaRepo orderRepository, BalloonService balloonService, BalloonJpaRepo balloonRepository) {
        this.orderRepository = orderRepository;
        this.balloonService = balloonService;
        this.balloonRepository = balloonRepository;
    }

    @Override
    public Order placeOrder(String balloonColor, String balloonSize, String clientName, String address, Long orderId) {
        return null;
    }

    @Override
    public List<Order> listOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Balloon> listAllBalloonsInOrder(Long orderId) {
        if(!this.orderRepository.findById(orderId).isPresent())
            throw new OrderNotFound(orderId);
        return this.orderRepository.findById(orderId).get().getBalloonsList();

    }
    @Override
    public Order getActiveOrder( String user) {

       User users=this.orderRepository.findByUser(user)
                .orElseThrow(()->new ProductNotFoundException());


        return  this.orderRepository
                .findByUserAndStatus(users,OrderStatus.CREATED)
                .orElseGet(()-> {
                    Order order=new Order(users);
                    return this.orderRepository.save(order);
                });






        /*return this.orderRepository
                .findByNameAndStatus(clientName, OrderStatus.CREATED)
                .orElseGet(() -> {

                    Order order =new Order(clientName);
                    return  this.orderRepository.save(order);

                });*/
    }



    @Override
    public Order addBalloonToOrder(String clientName, Long orderId) {
        Order order = this.getActiveOrder(clientName);
        Balloon balloon = this.balloonService.findById(orderId)
                .orElseThrow(() -> new OrderNotFound(orderId));
        if (order.getBalloonsList().stream().filter(i->i.getId().equals(orderId)).collect(Collectors.toList()).size()>0)
            throw new BalloonNotFoundException(orderId);
        order.getBalloonsList().add(balloon);
        return this.orderRepository.save(order);
    }


}
