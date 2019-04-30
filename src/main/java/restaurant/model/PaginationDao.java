package restaurant.model;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface PaginationDao extends PagingAndSortingRepository<Order, Integer> {

}