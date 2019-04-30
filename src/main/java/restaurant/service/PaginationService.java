package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import restaurant.model.Order;
import restaurant.model.PaginationDao;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class PaginationService {
    private PaginationDao paginationDao;
    @Autowired
    public PaginationService(PaginationDao paginationDao){

        this.paginationDao = paginationDao;
    }


    public Iterable<Order> findJsonDataByCondition(String orderBy, String direction, int page, int size) {
        Sort sort = null;
        if (direction.equals("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, orderBy);
        }
        if (direction.equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, orderBy);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Order> data = paginationDao.findAll(pageable);
        ArrayList<Order> list = new ArrayList<>();
        for (Iterator<Order> it = data.iterator(); it.hasNext(); ) {
            Order order = it.next();
            list.add(order);
        }
        return list;
    }
}
