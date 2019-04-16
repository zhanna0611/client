package restaurant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Order;
import restaurant.repository.TacoCloudClient;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@SessionAttributes("order")
public class OrderController {


    private TacoCloudClient tacoCloudClient;


    @Autowired
    public OrderController(TacoCloudClient tacoCloudClient) {
            this.tacoCloudClient=tacoCloudClient;
    }
//
    @ModelAttribute(name = "order")
    public Order order() {

        return new Order();
    }



//
//    @GetMapping("/")
//    public String orderForm(@AuthenticationPrincipal User user,
//                            @ModelAttribute Order order) {
//        if (order.getDeliveryName() == null) {
//            order.setDeliveryName(user.getFullname());
//        }
//        if (order.getDeliveryStreet() == null) {
//            order.setDeliveryStreet(user.getStreet());
//        }
//        if (order.getDeliveryCity() == null) {
//            order.setDeliveryCity(user.getCity());
//        }
//        if (order.getDeliveryState() == null) {
//            order.setDeliveryState(user.getState());
//        }
//        if (order.getDeliveryZip() == null) {
//            order.setDeliveryZip(user.getZip());
//        }
//
//        return "reservation";
//    }

//    @GetMapping("/")
//    public String orderForm() {
//
//        return "reservation";
//    }
//
//    @ModelAttribute(name = "order")
//    public Order order() {
//        return new Order();
//    }

    @GetMapping("/orderlist")
    public Iterable<Order> getAllOrders() {

        return  tacoCloudClient.getAllOrder();

    }

//
    @GetMapping("/")
     public String orderForm(Model model) {
     model.addAttribute("order", new Order());
    return "reservation";
}


    @PostMapping("/")
    public String processOrder(@Valid Order order, Errors errors) {
        if ( errors.hasErrors() ) {
            return "reservation";
        }

        //order.setUser(user);
       tacoCloudClient.saveOrder(order);

        //log.info("Order submitted: " + order);
        return "redirect:/orderlist";
    }


//
//    @GetMapping
//    public String ordersForUser(
//            @AuthenticationPrincipal User user, Model model) {
//
//        Pageable pageable = PageRequest.of(0, props.getPageSize());
//        model.addAttribute("orders",
//                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
//
//        return "orderList";
//    }

    @RequestMapping(value = {"/orderEdit", "/orderEdit/{id_reserve}"}, method = RequestMethod.GET)
    public String orderEditForm(@PathVariable(required = false, name = "id_reserve") long id_reserve) {

           tacoCloudClient.findByid(id_reserve);

        return "orderEdit";
    }

    @RequestMapping(value = "/orderEdit", method = RequestMethod.POST)
    public String orderEdit(Order order,Model model) {
        tacoCloudClient.saveOrder(order);
        model.addAttribute("order", tacoCloudClient.getAllOrder());
        return "orderlist";
    }
//
    @RequestMapping(value = "/orderDelete/{id_reserve}", method = RequestMethod.GET)
    public String orderDelete(@PathVariable(required = true, name = "id_reserve") long id_reserve, Model model) {
        Order order = tacoCloudClient.findByid(id_reserve);
        tacoCloudClient.deleteByid(id_reserve);
        model.addAttribute("orders", tacoCloudClient.getAllOrder());
        return "orderlist";
    }


}


