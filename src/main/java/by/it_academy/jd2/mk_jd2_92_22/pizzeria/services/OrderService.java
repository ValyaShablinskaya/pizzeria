package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService implements IOrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void add(Order order) {
        order.setCreationDate(LocalDateTime.now());
        orderDao.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Order is not found"));
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void update(Order order, Long id, LocalDateTime updateDate) {
        Order orderToUpdate = orderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order is not found"));
        if (!orderToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Order has been already edited");
        }
        orderToUpdate.setUpdateDate(LocalDateTime.now());

        orderDao.update(orderToUpdate);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Order orderToDelete= orderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order is not found"));
        if (!orderToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Order has been already edited");
        }
        orderDao.deleteById(id);
    }
}
