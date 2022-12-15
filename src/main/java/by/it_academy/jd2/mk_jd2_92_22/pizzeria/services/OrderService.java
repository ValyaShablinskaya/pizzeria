package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Transactional(readOnly = true)
public class OrderService implements IOrderService {
    private final IOrderDao orderDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Order is not found";

    public OrderService(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = IOrderMapper.INSTANCE.convertToOrder(orderDTO);
        order.setCreationDate(LocalDateTime.now());
        order.setUpdateDate(order.getCreationDate());
        order = orderDao.save(order);
        return IOrderMapper.INSTANCE.convertToOrderDTO(order);
    }

    @Override
    public OrderDTO read(Long id) {
        Order order = orderDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IOrderMapper.INSTANCE.convertToOrderDTO(order);
    }

    @Override
    public List<OrderDTO> get() {
        List<Order> orders = orderDao.findAll();
        return IOrderMapper.INSTANCE.convertToOrderList(orders);
    }

    @Override
    @Transactional
    public OrderDTO update(OrderDTO orderDTO, Long id, LocalDateTime updateDate) {
        Order orderToUpdate = orderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!orderToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Order has been already edited");
        }
        orderToUpdate.setUpdateDate(LocalDateTime.now());
        orderToUpdate = orderDao.save(orderToUpdate);

        return IOrderMapper.INSTANCE.convertToOrderDTO(orderToUpdate);
    }

    @Override
    public void delete(Long id, LocalDateTime updateDate) {
        Order orderToDelete= orderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!orderToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Order has been already edited");
        }
        orderDao.deleteById(id);
    }
}
