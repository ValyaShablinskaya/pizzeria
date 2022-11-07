package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.TicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.ITicketService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class TicketService implements ITicketService {

    private final TicketDao ticketDao;

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public void add(Ticket ticket) {
        ticket.setCreationDate(LocalDateTime.now());
        ticketDao.save(ticket);
    }

    @Override
    public Ticket findById(Long id) {
        return ticketDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket is not found"));
    }

    @Override
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Override
    public void update(Ticket ticket, Long id, LocalDateTime updateDate) {
        Ticket updateTicket = ticketDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Ticket is not found"));
        if (!updateTicket.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Ticket has been already edited");
        }
        updateTicket.setUpdateDate(LocalDateTime.now());
        updateTicket.setOrder(ticket.getOrder());
        ticketDao.update(updateTicket);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Ticket deleteTicket = ticketDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Ticket is not found"));
        if (!deleteTicket.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Ticket has been already edited");
        }
        ticketDao.deleteById(id);
    }
}
