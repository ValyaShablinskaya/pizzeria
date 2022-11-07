package by.it_academy.jd2.mk_jd2_92_22.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStatusDaoTest {
    private final static String PROPERTIES = "src/test/resources/h2.properties";
    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";

    private BDConnector connector;
    private TableCreator tableCreator;
    private SelectedItemDao selectedItemDao;
    private OrderDao orderDao;
    private TicketDao ticketDao;
    private OrderStatusDao orderStatusDao;
    private List<OrderStatus> orderStatuses = new ArrayList<>();
    private OrderStatus firstOrderStatus;
    private OrderStatus secondOrderStatus;

    @BeforeEach
    void prepareTable() {
        connector = new BDConnector(PROPERTIES);
        tableCreator = new TableCreator(connector);
        selectedItemDao = new SelectedItemDao(connector);
        orderStatusDao = new OrderStatusDao(connector);
        orderDao = new OrderDao(connector);
        ticketDao = new TicketDao(connector);

        createTestData();
    }

    @Test
    void saveShouldAddOrderStatusToTheDatabase() {
        Ticket ticket = ticketDao.findById(1L).orElse(null);

        OrderStatus orderStatus = OrderStatus.builder()
                .id(3L)
                .ticket(ticket)
                .isDone(true)
                .build();

        orderStatusDao.save(orderStatus);
        OrderStatus actual = orderStatusDao.findById(3L).get();

        assertEquals(orderStatus, actual);
    }

    @Test
    void findByIdShouldReturnOrderStatusWhenGetId() {
        OrderStatus actual = orderStatusDao.findById(1L).get();
        assertEquals(firstOrderStatus, actual);
    }

    @Test
    void findAllShouldReturnListOfOrderStatus() {
        List<OrderStatus> actual = orderStatusDao.findAll();
        assertEquals(orderStatuses, actual);
    }

    @Test
    void updateShouldUpdateOrderStatus() {
        Ticket ticket = ticketDao.findById(2L).orElse(null);

        OrderStatus expected = OrderStatus.builder()
                .id(1L)
                .ticket(ticket)
                .isDone(true)
                .build();

        orderStatusDao.update(expected);
        OrderStatus actual = orderStatusDao.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldDeleteOrderStatusWhenGetId() {
        orderStatusDao.deleteById(2L);
        OrderStatus actual = orderStatusDao.findById(2L).orElse(null);
        assertThat(actual).isNull();
    }
    private void createTestData() {
        tableCreator.runScript(SCRIPT_SQL);

        Order firstOrder = Order.builder()
                .id(1L)
                .build();

        Order secondOrder = Order.builder()
                .id(2L)
                .build();

        orderDao.save(firstOrder);
        orderDao.save(secondOrder);

        Ticket firstTicket = Ticket.builder()
                .id(1L)
                .order(firstOrder)
                .build();

        Ticket secondTicket = Ticket.builder()
                .id(2L)
                .order(secondOrder)
                .build();

        ticketDao.save(firstTicket);
        ticketDao.save(secondTicket);

        firstOrderStatus = OrderStatus.builder()
                .id(1L)
                .ticket(firstTicket)
                .isDone(true)
                .build();

        secondOrderStatus = OrderStatus.builder()
                .id(2L)
                .ticket(secondTicket)
                .isDone(true)
                .build();

        orderStatusDao.save(firstOrderStatus);
        orderStatusDao.save(secondOrderStatus);
        orderStatuses.add(firstOrderStatus);
        orderStatuses.add(secondOrderStatus);
    }
}
