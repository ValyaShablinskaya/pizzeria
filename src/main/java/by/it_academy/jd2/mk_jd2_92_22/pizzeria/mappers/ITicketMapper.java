package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ITicketMapper {
    ITicketMapper INSTANCE = Mappers.getMapper(ITicketMapper.class);

    TicketDTO convertToTicketDTO(Ticket ticket);

    Ticket convertToTicket(TicketDTO ticketDTO);
    List<TicketDTO> convertToTicketList(List<Ticket> tickets);
}
