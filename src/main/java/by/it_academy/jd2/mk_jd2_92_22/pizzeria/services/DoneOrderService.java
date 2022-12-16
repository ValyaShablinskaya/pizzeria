package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IDoneOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IDoneOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IDoneOrderService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.DoneOrderDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class DoneOrderService implements IDoneOrderService {
    private final IDoneOrderDao doneOrderDao;
    private final IDoneOrderMapper doneOrderMapper;
    private final ITicketMapper ticketMapper;
    private final IPizzaMapper pizzaMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "DoneOrder is not found";

    public DoneOrderService(IDoneOrderDao doneOrderDao, IDoneOrderMapper doneOrderMapper,
                            ITicketMapper ticketMapper, IPizzaMapper pizzaMapper) {
        this.doneOrderDao = doneOrderDao;
        this.doneOrderMapper = doneOrderMapper;
        this.ticketMapper = ticketMapper;
        this.pizzaMapper = pizzaMapper;
    }

    @Override
    @Transactional
    public DoneOrderDTO create(DoneOrderDTO doneOrderDTO) {
        DoneOrder doneOrder = doneOrderMapper.convertToDoneOrder(doneOrderDTO);
        doneOrder.setCreationDate(LocalDateTime.now());
        doneOrder.setUpdateDate(doneOrder.getCreationDate());
        doneOrder = doneOrderDao.save(doneOrder);
        return doneOrderMapper.convertToDoneOrderDTO(doneOrder);
    }

    @Override
    public DoneOrderDTO read(Long id) {
        DoneOrder doneOrder = doneOrderDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return  doneOrderMapper.convertToDoneOrderDTO(doneOrder);
    }

    @Override
    public List<DoneOrderDTO> get() {
        List<DoneOrder> doneOrders = doneOrderDao.findAll();
        return doneOrderMapper.convertToDoneOrderList(doneOrders);
    }

    @Override
    @Transactional
    public DoneOrderDTO update(DoneOrderDTO doneOrderDTO, Long id, LocalDateTime updateDate) {
        DoneOrder updateOrderDoneOrder = doneOrderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updateOrderDoneOrder.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("DoneOrder has been already edited");
        }
        updateOrderDoneOrder.setUpdateDate(LocalDateTime.now());
        Ticket ticket = ticketMapper.convertToTicket(doneOrderDTO.getTicket());
        updateOrderDoneOrder.setTicket(ticket);
        List<Pizza> pizzas = pizzaMapper.convertToPizzaListFromDto(doneOrderDTO.getPizzas());
        updateOrderDoneOrder.setPizzas(pizzas);
        updateOrderDoneOrder = doneOrderDao.save(updateOrderDoneOrder);
        return doneOrderMapper.convertToDoneOrderDTO(updateOrderDoneOrder);
    }

    @Override
    @Transactional
    public void delete(Long id, LocalDateTime updateDate) {
        DoneOrder deleteDoneOrder = doneOrderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteDoneOrder.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("DoneOrder has been already edited");
        }
        doneOrderDao.deleteById(id);
    }
}
