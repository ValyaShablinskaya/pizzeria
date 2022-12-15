package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.ITicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.ITicketService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.TicketDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Transactional(readOnly = true)
public class TicketService implements ITicketService {

    private final ITicketDao ticketDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Ticket is not found";

    public TicketService(ITicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    @Transactional
        public TicketDTO create(TicketDTO ticketDTO) {
        Ticket ticket = ITicketMapper.INSTANCE.convertToTicket(ticketDTO);
        ticket.setCreationDate(LocalDateTime.now());
        ticket.setUpdateDate(ticket.getCreationDate());
        ticket = ticketDao.save(ticket);
        return ITicketMapper.INSTANCE.convertToTicketDTO(ticket);
    }

    @Override
    public TicketDTO read(Long id) {
        Ticket ticket = ticketDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return ITicketMapper.INSTANCE.convertToTicketDTO(ticket);
    }

    @Override
    public List<TicketDTO> get() {
        List<Ticket> tickets = ticketDao.findAll();
        return ITicketMapper.INSTANCE.convertToTicketList(tickets);
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
        Order order = IOrderMapper.INSTANCE.convertToOrder(ticketDTO.getOrder());
        updateTicket.setOrder(order);
        updateTicket = ticketDao.save(updateTicket);
        return ITicketMapper.INSTANCE.convertToTicketDTO(updateTicket);
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
