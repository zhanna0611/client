package restaurant.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restaurant.model.Order;
import restaurant.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j

public class TacoCloudClient {

    public ArrayList<Order> getAllOrder() {
        try {
            RestTemplate rest = new RestTemplate();

//            return rest.exchange("http://localhost:8081/teachers/ps?orderBy=name&direction=DESC&page=0&size=5",
            return rest.exchange("http://localhost:8080/orderlist",
                    HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Order>>() {
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
            return rest.postForObject("http://localhost:8080/register/register1",
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


//    public void deleteById(long id_reserve) {
//        try {
//            RestTemplate rest = new RestTemplate();
//            Map<String, Long> urlVariables = new HashMap<>();
//            urlVariables.put("id", id_reserve);
//            rest.getForObject("http://localhost:8080/orderDelete/{id_reserve}",
//                    Order.class, urlVariables);
//        } catch (Exception e) {
//            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    public void deleteById(Long id_reserve) {
    try {
        System.out.println();
        RestTemplate rest = new RestTemplate();
        rest.delete("http://localhost:8080/orderDelete/{id_reserve}",
                id_reserve);
    } catch (Exception e) {
        System.err.println("Exception in TacoCloudClient: " + e.getMessage());
        e.printStackTrace();
    }
}


    public Order findById(Long id_reserve) {
        try {
            RestTemplate rest = new RestTemplate();
//            Map<String, Long> urlVariables = new HashMap<>();
//            urlVariables.put("id_reserve", id_reserve);
            return rest.getForObject("http://localhost:8080/orderEdit/{id_reserve}",
                    Order.class, id_reserve);
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Order> getAllOrdersBySorting(String orderBy, String direction, String page, String size) {
        try {
            RestTemplate rest = new RestTemplate();
            String url = "http://localhost:8080/ps?orderBy=" + orderBy + "&direction=" + direction;


            return rest.exchange(url+ "&page="+page+"&size="+size,
//            return rest.exchange("http://localhost:8081/teachers/ps?orderBy=name&direction=DESC&page=0&size=5",
//            return rest.exchange("http://localhost:8081/teachers",
                    HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Order>>() {
                    })
                    .getBody();
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
