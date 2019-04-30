package restaurant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Order;
import restaurant.repository.TacoCloudClient;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class OrderController {


    private PasswordEncoder passwordEncoder;
    HashMap<String ,String> direction = new HashMap<>();
    String orderBy;
    int page=0;
    int size=5;
    public OrderController(
            PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        direction.put("id_reserve","ASC");
        direction.put("date","ASC");
        direction.put("email","ASC");
        direction.put("name","ASC");
        direction.put("person","ASC");
        direction.put("phone","ASC");
    }

    @Autowired
    private TacoCloudClient tacoCloudClient= new TacoCloudClient();

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


    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if ( errors.hasErrors() ) {
            return "reservation";
        }

        //order.setUser(user);
       tacoCloudClient.saveOrder(order);

        //log.info("Order submitted: " + order);
        return "redirect:/orderlist";
    }

    @GetMapping("/orderBy/{orderBy}")
    public String getAllOrders(@PathVariable("orderBy") String orderBy1, Model model) {
//    public String getAllTeachers(Model model) {
//        String orderBy = "id";
        // This returns a JSON or XML with the users
        //return editTimetableRepository.findAll();
//        model.addAttribute("edits", timetableClient.getAllTeachersOrderBy(orderBy));
//        return "Edit_admin";

        orderBy = orderBy1.substring(1,orderBy1.length()-1);
//        model.addAttribute("order", timetableClient.getAllTeachersOrderBy(orderBy));
        model.addAttribute("edit", new Order());
        model.addAttribute("edits", tacoCloudClient.getAllOrdersBySorting(orderBy, direction.get(orderBy),
                Integer.toString(page),Integer.toString(size)));
        if(direction.get(orderBy).equals("ASC")){
            direction.replace(orderBy, "DESC");
        } else{
            direction.replace(orderBy, "ASC");
        }

        return "orderlist";
    }

    @GetMapping("/page/{page}")
    public String getAllOrdersForPage(@PathVariable("page") String page1, Model model) {
        if (orderBy != null) {


            String direction1;
            if (direction.get(orderBy).equals("ASC")) {
                direction1 = "DESC";
            } else {
                direction1 = "ASC";
            }
            if (page1.equals("{previous}") && page != 0) page -= 1;
            else if (page1.equals("{next}")) page++;
            model.addAttribute("edit", new Order());
            model.addAttribute("edits", tacoCloudClient.getAllOrdersBySorting(orderBy, direction1,
                    Integer.toString(page), Integer.toString(size)));
            return "orderlist";
        }
        model.addAttribute("edit", new Order());
        model.addAttribute("edits", tacoCloudClient.getAllOrder());
        return "orderlist";
    }

    @GetMapping("/size/{size}")
    public String getAllOrdersForSize(@PathVariable("size") String size1, Model model) {

        if(size1.equals("{five}")) size=5;
        else if(size1.equals("{ten}")) size = 10;
        else if(size1.equals("{twenty}")) size = 20;
        if (orderBy != null) {
            String direction1;
            if(direction.get(orderBy).equals("ASC")){
                direction1 = "DESC";
            }else{
                direction1 = "ASC";
            }

            model.addAttribute("edit", new Order());
            model.addAttribute("edits", tacoCloudClient.getAllOrdersBySorting(orderBy, direction1,
                    Integer.toString(page), Integer.toString(size)));
            return "orderlist";
        }
        model.addAttribute("edit", new Order());
        ArrayList<Order> list = new ArrayList<>(tacoCloudClient.getAllOrder());
        int s = size;
        if(size>list.size()) s = list.size();
        list = new ArrayList<>(list.subList(0,s));
        model.addAttribute("edits", list);
        return "orderlist";


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

           tacoCloudClient.findById(id_reserve);

        return "orderEdit";
    }

    @RequestMapping(value = "/orderEdit", method = RequestMethod.POST)
    public String orderEdit(Order order,Model model) {
        tacoCloudClient.saveOrder(order);
        model.addAttribute("order", tacoCloudClient.getAllOrder());
        return "orderlist";
    }

//    @GetMapping("/orderBy/{orderBy}")
//    public String getAllTeachers(@PathVariable("orderBy") String orderBy, Model model) {
////    public String getAllTeachers(Model model) {
////        String orderBy = "id";
//        // This returns a JSON or XML with the users
//        //return editTimetableRepository.findAll();
////        model.addAttribute("edits", timetableClient.getAllTeachersOrderBy(orderBy));
////        return "Edit_admin";
//        orderBy = orderBy.substring(1,orderBy.length()-1);
////        model.addAttribute("order", timetableClient.getAllTeachersOrderBy(orderBy));
//        model.addAttribute("orders", new Order());
//        model.addAttribute("orders", tacoCloudClient.getAllOrdersOrderBy(orderBy));
//        return "orderlist";
//    }
//
    @GetMapping("/orderDelete/{id_reserve}")
    public String deleteOrder(@PathVariable("id_reserve") long id_reserve, Model model) {

    //EditTimetable editTimetable = editTimetableRepository.findById(id)
    //EditTimetable editTimetable = timetableClient.getTeacherById(id);
    //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    //editTimetableRepository.delete(editTimetable);
        tacoCloudClient.deleteById(id_reserve);
        model.addAttribute("orders", tacoCloudClient.getAllOrder());
        return "orderlist";
}
//    @RequestMapping(value = "/orderDelete/{id_reserve}", method = RequestMethod.GET)
//    public String orderDelete(@PathVariable("id_reserve") long id_reserve, Model model) {
//         //tacoCloudClient.findById(id_reserve);
//
//        model.addAttribute("orders", tacoCloudClient.getAllOrder());
//        return "orderlist";
//    }

//    @GetMapping("/orderBy/{orderBy}")
//    public String getAllTeachers(@PathVariable("orderBy") String orderBy, Model model) {
////    public String getAllTeachers(Model model) {
////        String orderBy = "id";
//        // This returns a JSON or XML with the users
//        //return editTimetableRepository.findAll();
////        model.addAttribute("edits", timetableClient.getAllTeachersOrderBy(orderBy));
////        return "Edit_admin";
//        orderBy = orderBy.substring(1,orderBy.length()-1);
////        model.addAttribute("order", timetableClient.getAllTeachersOrderBy(orderBy));
//        model.addAttribute("edit", new Order());
//        model.addAttribute("edits", tacoCloudClient.getAllOrdersOrderBy(orderBy));
//        return "orderlist";
//    }

}


