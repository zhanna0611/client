package restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Order;
import restaurant.repository.OrderRepository;
import restaurant.service.OrderService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
       return "reservation";
    }
    @GetMapping("/orderlist")
    public String orderList(Model model) {
        model.addAttribute("order", orderRepository.findAll());
        return "orderlist";
    }


    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "reservation";
        }

        orderRepository.save(order);
        //log.info("Order submitted: " + order);
        return "redirect:/orderlist";
    }

    @RequestMapping(value={"/orderEdit","/orderEdit/{id_reserve}"}, method = RequestMethod.GET)
    public String orderEditForm(Model model, @PathVariable(required = false, name = "id_reserve") Long id_reserve) {
        if (null != id_reserve) {
            model.addAttribute("order", orderRepository.findById(id_reserve).orElse(null));
        } else {
            model.addAttribute("order", new Order());
        }
        return "orderEdit";
    }

    @RequestMapping(value="/orderEdit", method = RequestMethod.POST)
    public String orderEdit(Model model, Order order) {
        orderRepository.save(order);
        model.addAttribute("order", orderRepository.findAll());
        return "orderlist";
    }

    @RequestMapping(value="/orderDelete/{id_reserve}", method = RequestMethod.GET)
    public String orderDelete(Model model, @PathVariable(required = true, name = "id_reserve") Long id_reserve) {
        Order order = orderRepository.findById(id_reserve).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id_reserve));
        orderRepository.delete(order);
        model.addAttribute("order", orderRepository.findAll());
        return "orderlist";
    }


}


