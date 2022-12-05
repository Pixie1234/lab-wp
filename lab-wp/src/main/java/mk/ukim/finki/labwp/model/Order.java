package mk.ukim.finki.labwp.model;

import lombok.Data;
import mk.ukim.finki.labwp.model.enumerations.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="user_orders")
public class Order {
    private String balloonColor;
    private String balloonSize;
    //private String clientName;
    //private String clientAddress;


    @ManyToOne
    private User user;


    @Id
    private Long orderId;
    @ManyToMany
    private List<Balloon>balloonsList;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {

    }


    public Order(User user) {

        this.balloonsList=new ArrayList<>();
    this.user=user;
        //this.clientAddress = clientAddress;
        this.status = OrderStatus.CREATED;

    }
}
