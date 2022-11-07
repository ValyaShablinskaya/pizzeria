package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderStatusService {
    void add(OrderStatus orderStatus);
    OrderStatus findById(Long id);
    List<OrderStatus> findAll();
    void update(OrderStatus orderStatus, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
}
