package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.DoneOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.DoneOrderService;
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
public class DoneOrderServiceTest {
    @Mock
    private DoneOrderDao doneOrderDao;
    @InjectMocks
    private DoneOrderService doneOrderService;

    @Test
    void addDoneOrderShouldReturnCorrectResult() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        DoneOrder doneOrder = DoneOrder.builder()
                .id(1L)
                .ticket(ticket)
                .build();

        doNothing().when(doneOrderDao).save(doneOrder);

        doneOrderService.add(doneOrder);
        verify(doneOrderDao).save(doneOrder);
    }

    @Test
    void findDoneOrderByIdShouldReturnCorrectResult() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        DoneOrder doneOrder = DoneOrder.builder()
                .id(1L)
                .ticket(ticket)
                .build();

        when(doneOrderDao.findById(doneOrder.getId())).thenReturn(Optional.of(doneOrder));
        doneOrderService.findById(doneOrder.getId());
        verify(doneOrderDao).findById(doneOrder.getId());
    }

    @Test
    void findDoneOrderByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        DoneOrder doneOrder = DoneOrder.builder()
                .id(1L)
                .ticket(ticket)
                .build();

        when(doneOrderDao.findById(doneOrder.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> doneOrderService.findById(doneOrder.getId()));
        verifyNoMoreInteractions(doneOrderDao);
    }

    @Test
    void findAllDoneOrdersShouldReturnCorrectResult() {
        List<DoneOrder> doneOrders = new ArrayList<>();

        doneOrders.add(DoneOrder.builder()
                .id(1L)
                .build());

        doneOrders.add(DoneOrder.builder()
                .id(2L)
                .build());


        when(doneOrderDao.findAll()).thenReturn(doneOrders);
        doneOrderService.findAll();
        verify(doneOrderDao).findAll();
    }

    @Test
    void updateDoneOrderShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        DoneOrder doneOrder = DoneOrder.builder()
                .id(1L)
                .ticket(ticket)
                .updateDate(updateData)
                .build();

        when(doneOrderDao.findById(doneOrder.getId())).thenReturn(Optional.of(doneOrder));
        doNothing().when(doneOrderDao).update(doneOrder);
        doneOrderService.update(doneOrder, doneOrder.getId(), updateData);
        verify(doneOrderDao).update(doneOrder);
    }

    @Test
    void removeDoneOrderByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        DoneOrder doneOrder = DoneOrder.builder()
                .id(1L)
                .ticket(ticket)
                .updateDate(updateData)
                .build();

        when(doneOrderDao.findById(doneOrder.getId())).thenReturn(Optional.of(doneOrder));
        doneOrderService.deleteById(doneOrder.getId(), updateData);
        verify(doneOrderDao).deleteById(doneOrder.getId());
    }
}
