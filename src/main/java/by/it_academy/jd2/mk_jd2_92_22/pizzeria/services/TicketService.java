package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.TicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.ITicketService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.TicketDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class TicketService implements ITicketService {

    private final TicketDao ticketDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Ticket is not found";

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public TicketDTO add(TicketDTO ticketDTO) {
        Ticket ticket = ITicketMapper.INSTANCE.convertToTicket(ticketDTO);
        ticket.setCreationDate(LocalDateTime.now());
        ticket.setUpdateDate(ticket.getCreationDate());
        ticket = ticketDao.save(ticket).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return ITicketMapper.INSTANCE.convertToTicketDTO(ticket);
    }

    @Override
    public TicketDTO findById(Long id) {
        Ticket ticket = ticketDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return ITicketMapper.INSTANCE.convertToTicketDTO(ticket);
    }

    @Override
    public List<TicketDTO> findAll() {
        List<Ticket> tickets = ticketDao.findAll();
        return ITicketMapper.INSTANCE.convertToTicketList(tickets);
    }

    @Override
    public TicketDTO update(TicketDTO ticketDTO, Long id, LocalDateTime updateDate) {
        Ticket updateTicket = ticketDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updateTicket.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Ticket has been already edited");
        }
        updateTicket.setUpdateDate(LocalDateTime.now());
        Order order = IOrderMapper.INSTANCE.convertToOrder(ticketDTO.getOrder());
        updateTicket.setOrder(order);
        updateTicket = ticketDao.update(updateTicket).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return ITicketMapper.INSTANCE.convertToTicketDTO(updateTicket);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Ticket deleteTicket = ticketDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteTicket.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Ticket has been already delete");
        }
        ticketDao.deleteById(id);
    }
}
