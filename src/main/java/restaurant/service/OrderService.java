package restaurant.service;

import restaurant.model.Order;

import java.util.List;


public interface OrderService {
    public List<Order> findAll();

    Order findOne(Long id_reserve);

    public Order saveOrder(Order order);

    public void deleteOrder(Long id_reserve);
}
