package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderStatusMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderStatusService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderStatusDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class OrderStatusService implements IOrderStatusService {
    private final OrderStatusDao orderStatusDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "OrderStatus is not found";

    public OrderStatusService(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public OrderStatusDTO add(OrderStatusDTO orderStatusDTO) {
        OrderStatus orderStatus = IOrderStatusMapper.INSTANCE.convertToOrderStatus(orderStatusDTO);
       orderStatus.setCreationDate(LocalDateTime.now());
       orderStatus.setUpdateDate(orderStatus.getCreationDate());
       orderStatus = orderStatusDao.save(orderStatus).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
       return IOrderStatusMapper.INSTANCE.convertToOrderStatusDTO(orderStatus);
    }

    @Override
    public OrderStatusDTO findById(Long id) {
        OrderStatus orderStatus = orderStatusDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IOrderStatusMapper.INSTANCE.convertToOrderStatusDTO(orderStatus);
    }

    @Override
    public List<OrderStatusDTO> findAll() {
        List<OrderStatus> orderStatuses = orderStatusDao.findAll();
        return IOrderStatusMapper.INSTANCE.convertToOrderStatusList(orderStatuses);
    }

    @Override
    public OrderStatusDTO update(OrderStatusDTO orderStatusDTO, Long id, LocalDateTime updateDate) {
        OrderStatus updateOrderStatus = orderStatusDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updateOrderStatus.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("OrderStatus has been already edited");
        }
        updateOrderStatus.setUpdateDate(LocalDateTime.now());
        Ticket ticket = ITicketMapper.INSTANCE.convertToTicket(orderStatusDTO.getTicket());
        updateOrderStatus.setTicket(ticket);
        updateOrderStatus.setDone(orderStatusDTO.isDone());
        updateOrderStatus = orderStatusDao.update(updateOrderStatus).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IOrderStatusMapper.INSTANCE.convertToOrderStatusDTO(updateOrderStatus);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        OrderStatus deleteOrderStatus = orderStatusDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteOrderStatus.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("OrderStatus has been already delete");
        }
        orderStatusDao.deleteById(id);
    }
}
