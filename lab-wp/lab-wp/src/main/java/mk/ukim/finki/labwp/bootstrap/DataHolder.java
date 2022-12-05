package mk.ukim.finki.labwp.bootstrap;

import lombok.Getter;
import mk.ukim.finki.labwp.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Component
@Getter
public class DataHolder {
    public  static List<Balloon>balloons=new ArrayList<>();
    public static List<Manufacturer>manufacturers=new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static List<User> users=new ArrayList<>();

    /*@PostConstruct
    public void init(){

        Manufacturer manufacturer=new Manufacturer(" Apple", " Usa","");
        manufacturers.add(manufacturer);
        manufacturers.add(new Manufacturer("Apple", "USA", "Los Angeles"));
        manufacturers.add(new Manufacturer("Huawei", "USA", "Chicago"));
        manufacturers.add(new Manufacturer("Tesla", "USA", "Chicago"));
        manufacturers.add(new Manufacturer("Apple", "USA", "Chicago"));


        balloons.add(new Balloon("red", "medium",manufacturer));
        balloons.add(new Balloon("yellow", "big",manufacturer));
        balloons.add(new Balloon("pink", "small",manufacturer));
        balloons.add(new Balloon("blue", "medium",manufacturer));
        balloons.add(new Balloon("black", "big",manufacturer));
        balloons.add(new Balloon("green", "small",manufacturer));
        balloons.add(new Balloon("orange", "big",manufacturer));
        balloons.add(new Balloon("pink", "big",manufacturer));
        balloons.add(new Balloon("yellow", "small",manufacturer));
        balloons.add(new Balloon("red", "small",manufacturer));

        orders.add(new Order(balloons));





    }*/

}

