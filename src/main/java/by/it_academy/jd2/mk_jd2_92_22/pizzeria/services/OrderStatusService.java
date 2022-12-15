package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderStatusMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderStatusService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderStatusDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Transactional(readOnly = true)
public class OrderStatusService implements IOrderStatusService {
    private final IOrderStatusDao orderStatusDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "OrderStatus is not found";

    public OrderStatusService(IOrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderStatusDTO create(OrderStatusDTO orderStatusDTO) {
        OrderStatus orderStatus = IOrderStatusMapper.INSTANCE.convertToOrderStatus(orderStatusDTO);
       orderStatus.setCreationDate(LocalDateTime.now());
       orderStatus.setUpdateDate(orderStatus.getCreationDate());
       orderStatus = orderStatusDao.save(orderStatus);
       return IOrderStatusMapper.INSTANCE.convertToOrderStatusDTO(orderStatus);
    }

    @Override
    public OrderStatusDTO read(Long id) {
        OrderStatus orderStatus = orderStatusDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IOrderStatusMapper.INSTANCE.convertToOrderStatusDTO(orderStatus);
    }

    @Override
    public List<OrderStatusDTO> get() {
        List<OrderStatus> orderStatuses = orderStatusDao.findAll();
        return IOrderStatusMapper.INSTANCE.convertToOrderStatusList(orderStatuses);
    }

    @Override
    @Transactional(readOnly = true)
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
        updateOrderStatus = orderStatusDao.save(updateOrderStatus);
        return IOrderStatusMapper.INSTANCE.convertToOrderStatusDTO(updateOrderStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id, LocalDateTime updateDate) {
        OrderStatus deleteOrderStatus = orderStatusDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteOrderStatus.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("OrderStatus has been already delete");
        }
        orderStatusDao.deleteById(id);
    }
}
