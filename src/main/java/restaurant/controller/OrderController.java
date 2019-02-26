package restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/")
public class OrderController {
    @GetMapping("/")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "reservation";
    }
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "reservation";
        }
        //log.info("Order submitted: " + order);
        return "redirect:/about";
    }
}
