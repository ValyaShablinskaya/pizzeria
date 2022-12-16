package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.TicketDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITicketMapper {

    TicketDTO convertToTicketDTO(Ticket ticket);

    Ticket convertToTicket(TicketDTO ticketDTO);
    List<TicketDTO> convertToTicketList(List<Ticket> tickets);
}
