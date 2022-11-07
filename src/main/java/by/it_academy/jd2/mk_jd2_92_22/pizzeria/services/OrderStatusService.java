package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderStatusService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class OrderStatusService implements IOrderStatusService {
    private final OrderStatusDao orderStatusDao;

    public OrderStatusService(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public void add(OrderStatus orderStatus) {
       orderStatus.setCreationDate(LocalDateTime.now());
       orderStatusDao.save(orderStatus);
    }

    @Override
    public OrderStatus findById(Long id) {
        return orderStatusDao.findById(id).orElseThrow(() -> new EntityNotFoundException("OrderStatus is not found"));
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusDao.findAll();
    }

    @Override
    public void update(OrderStatus orderStatus, Long id, LocalDateTime updateDate) {
        OrderStatus updateOrderStatus = orderStatusDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("OrderStatus is not found"));
        if (!updateOrderStatus.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("OrderStatus has been already edited");
        }
        updateOrderStatus.setUpdateDate(LocalDateTime.now());
        updateOrderStatus.setTicket(orderStatus.getTicket());
        updateOrderStatus.setDone(orderStatus.isDone());
        orderStatusDao.update(updateOrderStatus);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        OrderStatus deleteOrderStatus = orderStatusDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("OrderStatus is not found"));
        if (!deleteOrderStatus.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("OrderStatus has been already edited");
        }
        orderStatusDao.deleteById(id);
    }
}
