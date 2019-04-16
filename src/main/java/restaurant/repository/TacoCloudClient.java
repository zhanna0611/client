package restaurant.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restaurant.model.Order;
import restaurant.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TacoCloudClient {

    public List<Order> getAllOrder() {
        try {
            RestTemplate rest = new RestTemplate();

            return rest.exchange("http://localhost:8080/orderlist",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                    })
                    .getBody();
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Order saveOrder(Order order) {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.postForObject("http://localhost:8080/save",
                    order, Order.class);
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

//    public List<Ingredient> getAllIngredients() {
//        try {
//            RestTemplate rest = new RestTemplate();
//
//            return rest.exchange("http://localhost:8080/taco/ingredients",
//                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {
//                    })
//                    .getBody();
//        } catch (Exception e) {
//            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }

    public User saveUser(User user) {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.postForObject("http://localhost:8080/register",
                    user, User.class);
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String username) {
    try {
        RestTemplate rest = new RestTemplate();
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("username", username);
        return rest.getForObject("http://localhost:8080/user/{username}",
                User.class, urlVariables);
    } catch (Exception e) {
        System.err.println("Exception in TacoCloudClient: " + e.getMessage());
        e.printStackTrace();
    }
    return null;
}


    public Order deleteByid(long id_reserve) {
        try {
            RestTemplate rest = new RestTemplate();
            Map<String, Long> urlVariables = new HashMap<>();
            urlVariables.put("id", id_reserve);
            return rest.getForObject("http://localhost:8080/orderDelete{id_reserve}",
                    Order.class, urlVariables);
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Order findByid(long id_reserve) {
        try {
            RestTemplate rest = new RestTemplate();
            Map<String, Long> urlVariables = new HashMap<>();
            urlVariables.put("id", id_reserve);
            return rest.getForObject("http://localhost:8080/orderEdit/{id_reserve}",
                    Order.class, urlVariables);
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}


//    public List<Order> getAllOrders() {
//        try {
//            RestTemplate rest = new RestTemplate();
//
//            return rest.exchange("http://localhost:8080/orderlist",
//                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {})
//                    .getBody();
//        } catch (Exception e) {
//            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Order saveOrder(Order order) {
//        try {
//            RestTemplate rest = new RestTemplate();
//            return rest.postForObject("http://localhost:8080/save",
//                    order, Order.class);
//        } catch (Exception e) {
//            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public User saveUser(User user) {
//        try {
//            RestTemplate rest = new RestTemplate();
//            return rest.postForObject("http://localhost:8080/user/register",
//                    user, User.class);
//        } catch (Exception e) {
//            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public User getUserByUsername(String username)  {
//        try {
//            RestTemplate rest = new RestTemplate();
//            Map<String, String> urlVariables = new HashMap<>();
//            urlVariables.put("username", username);
//            return rest.getForObject("http://localhost:8080/restaurant/user/{username}",
//                    User.class, urlVariables);
//        } catch (Exception e) {
//            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
