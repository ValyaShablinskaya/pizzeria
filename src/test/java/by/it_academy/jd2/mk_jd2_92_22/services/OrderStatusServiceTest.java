package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.OrderStatusService;
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
public class OrderStatusServiceTest {
    @Mock
    private OrderStatusDao orderStatusDao;
    @InjectMocks
    private OrderStatusService orderStatusService;

    @Test
    void addSelectedItemShouldReturnCorrectResult() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        OrderStatus orderStatus = OrderStatus.builder()
                .id(3L)
                .ticket(ticket)
                .isDone(true)
                .build();

        doNothing().when(orderStatusDao).save(orderStatus);

        orderStatusService.add(orderStatus);
        verify(orderStatusDao).save(orderStatus);
    }

    @Test
    void findSelectedItemByIdShouldReturnCorrectResult() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        OrderStatus orderStatus = OrderStatus.builder()
                .id(3L)
                .ticket(ticket)
                .isDone(true)
                .build();

        when(orderStatusDao.findById(orderStatus.getId())).thenReturn(Optional.of(orderStatus));
        orderStatusService.findById(orderStatus.getId());
        verify(orderStatusDao).findById(orderStatus.getId());
    }

    @Test
    void findSelectedItemByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        OrderStatus orderStatus = OrderStatus.builder()
                .id(3L)
                .ticket(ticket)
                .isDone(true)
                .build();

        when(orderStatusDao.findById(orderStatus.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderStatusService.findById(orderStatus.getId()));
        verifyNoMoreInteractions(orderStatusDao);
    }

    @Test
    void findAllSelectedItemsShouldReturnCorrectResult() {
        List<OrderStatus> orderStatuses = new ArrayList<>();

        orderStatuses.add(OrderStatus.builder()
                .id(3L)
                .isDone(true)
                .build());

        orderStatuses.add(OrderStatus.builder()
                .id(4L)
                .isDone(true)
                .build());

        when(orderStatusDao.findAll()).thenReturn(orderStatuses);
        orderStatusService.findAll();
        verify(orderStatusDao).findAll();
    }

    @Test
    void updateSelectedItemShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        OrderStatus orderStatus = OrderStatus.builder()
                .id(3L)
                .ticket(ticket)
                .isDone(true)
                .updateDate(updateData)
                .build();

        when(orderStatusDao.findById(orderStatus.getId())).thenReturn(Optional.of(orderStatus));
        doNothing().when(orderStatusDao).update(orderStatus);
        orderStatusService.update(orderStatus, orderStatus.getId(), updateData);
        verify(orderStatusDao).update(orderStatus);
    }

    @Test
    void removeSelectedItemByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Order order = Order.builder()
                .id(1L)
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();

        OrderStatus orderStatus = OrderStatus.builder()
                .id(3L)
                .ticket(ticket)
                .isDone(true)
                .updateDate(updateData)
                .build();

        when(orderStatusDao.findById(orderStatus.getId())).thenReturn(Optional.of(orderStatus));
        orderStatusService.deleteById(orderStatus.getId(), updateData);
        verify(orderStatusDao).deleteById(orderStatus.getId());
    }
}
