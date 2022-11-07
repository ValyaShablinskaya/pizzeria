package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.OrderService;
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
public class OrderServiceTest {
    @Mock
    private OrderDao orderDao;
    @InjectMocks
    private OrderService orderService;

    @Test
    void addOrderShouldReturnCorrectResult() {
        Order order = Order.builder()
                .id(3L)
                .build();

        doNothing().when(orderDao).save(order);

        orderService.add(order);
        verify(orderDao).save(order);
    }

    @Test
    void findOrderByIdShouldReturnCorrectResult() {
        Order order = Order.builder()
                .id(3L)
                .build();

        when(orderDao.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.findById(order.getId());
        verify(orderDao).findById(order.getId());
    }

    @Test
    void findOrderByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        Order order = Order.builder()
                .id(3L)
                .build();

        when(orderDao.findById(order.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderService.findById(order.getId()));
        verifyNoMoreInteractions(orderDao);
    }

    @Test
    void findAllOrdersShouldReturnCorrectResult() {
        List<Order> orders = new ArrayList<>();

        orders.add(Order.builder()
                .id(3L)
                .build());

        orders.add(Order.builder()
                .id(4L)
                .build());

        when(orderDao.findAll()).thenReturn(orders);
        orderService.findAll();
        verify(orderDao).findAll();
    }

    @Test
    void updateOrderShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Order order = Order.builder()
                .id(3L)
                .updateDate(updateData)
                .build();

        when(orderDao.findById(order.getId())).thenReturn(Optional.of(order));
        doNothing().when(orderDao).update(order);
        orderService.update(order, order.getId(), updateData);
        verify(orderDao).update(order);
    }

    @Test
    void removeMenuByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Order order = Order.builder()
                .id(3L)
                .updateDate(updateData)
                .build();

        when(orderDao.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.deleteById(order.getId(), updateData);
        verify(orderDao).deleteById(order.getId());
    }
}
