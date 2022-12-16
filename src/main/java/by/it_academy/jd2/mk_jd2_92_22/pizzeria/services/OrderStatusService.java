package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderStatusMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IStageMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderStatusService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderStatusDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class OrderStatusService implements IOrderStatusService {
    private final IOrderStatusDao orderStatusDao;
    private final IOrderStatusMapper orderStatusMapper;
    private final IStageMapper stageMapper;
    private final ITicketMapper ticketMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "OrderStatus is not found";

    public OrderStatusService(IOrderStatusDao orderStatusDao, IOrderStatusMapper orderStatusMapper,
                              IStageMapper stageMapper, ITicketMapper ticketMapper) {
        this.orderStatusDao = orderStatusDao;
        this.orderStatusMapper = orderStatusMapper;
        this.stageMapper = stageMapper;
        this.ticketMapper = ticketMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderStatusDTO create(OrderStatusDTO orderStatusDTO) {
        OrderStatus orderStatus = orderStatusMapper.convertToOrderStatus(orderStatusDTO);
       orderStatus.setCreationDate(LocalDateTime.now());
       orderStatus.setUpdateDate(orderStatus.getCreationDate());
       orderStatus = orderStatusDao.save(orderStatus);
       return orderStatusMapper.convertToOrderStatusDTO(orderStatus);
    }

    @Override
    public OrderStatusDTO read(Long id) {
        OrderStatus orderStatus = orderStatusDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return orderStatusMapper.convertToOrderStatusDTO(orderStatus);
    }

    @Override
    public List<OrderStatusDTO> get() {
        List<OrderStatus> orderStatuses = orderStatusDao.findAll();
        return orderStatusMapper.convertToOrderStatusList(orderStatuses);
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
        Ticket ticket = ticketMapper.convertToTicket(orderStatusDTO.getTicket());
        updateOrderStatus.setTicket(ticket);
        List<Stage> stage = stageMapper.convertToStageListFromDto(orderStatusDTO.getHistory());
        updateOrderStatus.setHistory(stage);
        updateOrderStatus.setDone(orderStatusDTO.isDone());
        updateOrderStatus = orderStatusDao.save(updateOrderStatus);
        return orderStatusMapper.convertToOrderStatusDTO(updateOrderStatus);
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
