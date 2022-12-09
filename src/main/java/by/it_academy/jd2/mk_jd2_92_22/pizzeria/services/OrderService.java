package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService implements IOrderService {
    private final OrderDao orderDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Order is not found";

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public OrderDTO add(OrderDTO orderDTO) {
        Order order = IOrderMapper.INSTANCE.convertToOrder(orderDTO);
        order.setCreationDate(LocalDateTime.now());
        order.setUpdateDate(order.getCreationDate());
        order = orderDao.save(order).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IOrderMapper.INSTANCE.convertToOrderDTO(order);
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = orderDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IOrderMapper.INSTANCE.convertToOrderDTO(order);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = orderDao.findAll();
        return IOrderMapper.INSTANCE.convertToOrderList(orders);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO, Long id, LocalDateTime updateDate) {
        Order orderToUpdate = orderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!orderToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Order has been already edited");
        }
        orderToUpdate.setUpdateDate(LocalDateTime.now());
        orderToUpdate = orderDao.update(orderToUpdate).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));

        return IOrderMapper.INSTANCE.convertToOrderDTO(orderToUpdate);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Order orderToDelete= orderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!orderToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Order has been already edited");
        }
        orderDao.deleteById(id);
    }
}
