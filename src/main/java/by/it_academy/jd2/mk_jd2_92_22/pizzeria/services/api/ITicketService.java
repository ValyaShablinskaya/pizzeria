package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.TicketDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ITicketService extends IEssenceService<TicketDTO>{
    TicketDTO add(TicketDTO ticket);
    TicketDTO findById(Long id);
    List<TicketDTO> findAll();
    TicketDTO update(TicketDTO ticket, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
}
