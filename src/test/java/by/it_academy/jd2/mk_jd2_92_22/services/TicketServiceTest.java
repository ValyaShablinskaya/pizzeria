package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.TicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.TicketService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TicketDao ticketDao;
    @InjectMocks
    private TicketService ticketService;

    @Test
    void addTicketShouldReturnCorrectResult() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(3L)
                .order(order)
                .build();

        when(ticketDao.save(ticket)).thenReturn(Optional.of(ticket));
        ticketService.add(ticket);
        verify(ticketDao).save(ticket);
    }

    @Test
    void findTicketByIdShouldReturnCorrectResult() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(3L)
                .order(order)
                .build();

        when(ticketDao.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        ticketService.findById(ticket.getId());
        verify(ticketDao).findById(ticket.getId());
    }

    @Test
    void findTicketByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(3L)
                .order(order)
                .build();

        when(ticketDao.findById(ticket.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> ticketService.findById(ticket.getId()));
        verifyNoMoreInteractions(ticketDao);
    }

    @Test
    void findAllTicketsShouldReturnCorrectResult() {
        List<Ticket> tickets = new ArrayList<>();
        Order order1 = Order.builder()
                .id(1L)
                .build();
        Order order2 = Order.builder()
                .id(2L)
                .build();

        tickets.add(Ticket.builder()
                .id(1L)
                .order(order1)
                .build());

        tickets.add(Ticket.builder()
                .id(2L)
                .order(order2)
                .build());

        when(ticketDao.findAll()).thenReturn(tickets);
        ticketService.findAll();
        verify(ticketDao).findAll();
    }

    @Test
    void updateTicketShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(3L)
                .order(order)
                .updateDate(updateData)
                .build();

        when(ticketDao.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        when(ticketDao.update(ticket)).thenReturn(Optional.of(ticket));
        ticketService.update(ticket, ticket.getId(), updateData);
        verify(ticketDao).update(ticket);
    }

    @Test
    void removeTicketByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(3L)
                .order(order)
                .updateDate(updateData)
                .build();

        when(ticketDao.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        ticketService.deleteById(ticket.getId(), updateData);
        verify(ticketDao).deleteById(ticket.getId());
    }
}
