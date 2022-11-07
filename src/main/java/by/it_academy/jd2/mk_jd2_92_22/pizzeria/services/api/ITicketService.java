package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.TicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface ITicketService {
    void add(Ticket ticket);
    Ticket findById(Long id);
    List<Ticket> findAll();
    void update(Ticket ticket, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
}
