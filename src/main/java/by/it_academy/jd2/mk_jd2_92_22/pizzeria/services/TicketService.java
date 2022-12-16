package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.ITicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.ITicketService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.TicketDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TicketService implements ITicketService {

    private final ITicketDao ticketDao;
    private final ITicketMapper ticketMapper;
    private final IOrderMapper orderMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Ticket is not found";

    public TicketService(ITicketDao ticketDao, ITicketMapper ticketMapper, IOrderMapper orderMapper) {
        this.ticketDao = ticketDao;
        this.ticketMapper = ticketMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
        public TicketDTO create(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.convertToTicket(ticketDTO);
        ticket.setCreationDate(LocalDateTime.now());
        ticket.setUpdateDate(ticket.getCreationDate());
        ticket = ticketDao.save(ticket);
        return ticketMapper.convertToTicketDTO(ticket);
    }

    @Override
    public TicketDTO read(Long id) {
        Ticket ticket = ticketDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return ticketMapper.convertToTicketDTO(ticket);
    }

    @Override
    public List<TicketDTO> get() {
        List<Ticket> tickets = ticketDao.findAll();
        return ticketMapper.convertToTicketList(tickets);
    }

    @Override
    @Transactional
    public TicketDTO update(TicketDTO ticketDTO, Long id, LocalDateTime updateDate) {
        Ticket updateTicket = ticketDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updateTicket.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Ticket has been already edited");
        }
        updateTicket.setUpdateDate(LocalDateTime.now());
        Order order = orderMapper.convertToOrder(ticketDTO.getOrder());
        updateTicket.setOrder(order);
        updateTicket = ticketDao.save(updateTicket);
        return ticketMapper.convertToTicketDTO(updateTicket);
    }

    @Override
    public void delete(Long id, LocalDateTime updateDate) {
        Ticket deleteTicket = ticketDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteTicket.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Ticket has been already delete");
        }
        ticketDao.deleteById(id);
    }
}
