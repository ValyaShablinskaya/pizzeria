package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {
    void add(Order order);
    Order findById(Long id);
    List<Order> findAll();
    void update(Order order, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
}
