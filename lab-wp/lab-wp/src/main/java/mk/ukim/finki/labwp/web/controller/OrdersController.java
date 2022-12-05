package mk.ukim.finki.labwp.web.controller;
import mk.ukim.finki.labwp.model.Order;
import mk.ukim.finki.labwp.repository.IMPL.OrderRepository;
import mk.ukim.finki.labwp.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;



    public OrdersController(OrderService orderService, OrderRepository orderRepository) {

        this.orderService = orderService;
        this.orderRepository = orderRepository;


    }

    @GetMapping
    //public String getOrderPage(@PathVariable("clientName") String clientName,HttpServletRequest req, Model model){
    public String getOrderPage( @RequestParam(required = false)  Model model){
    List<Order>orders=this.orderService.listOrders();
    model.addAttribute("orders",orders);



        /*Order order=this.orderService.getActiveOrder(clientName);
        model.addAttribute("balloons",this.orderService.listAllBalloonsInOrder(order.getOrderId()));*/
            return "userOrders";



        /*String clientName=(String) req.getSession().getAttribute("clientName");
        req.getSession().setAttribute("clientName",clientName);
        //Optional<Order> clientname=this.orderRepository.findByUsername(clientName);


        Order order=this.orderService.getActiveOrder(clientName);
        model.addAttribute("balloons",this.orderService.listAllBalloonsInOrder(order.getOrderId()));

        return "userOrders";*/


    }

















    /* @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        try{
            Order clientname = (Order) req.getSession().getAttribute("clientName");
            Order order = this.orderService.addBalloonToOrder(clientname.getClientName(), id);
            return "redirect:/ConfirmationInfo";
        }catch (RuntimeException exception) {
            return "redirect:/ConfirmationInfo?error=" + exception.getMessage();
        }
    }*/


}
