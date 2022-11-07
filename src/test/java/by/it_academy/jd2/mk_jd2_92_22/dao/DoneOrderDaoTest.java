package by.it_academy.jd2.mk_jd2_92_22.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneOrderDaoTest {
    private final static String PROPERTIES = "src/test/resources/h2.properties";
    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";

    private BDConnector connector;
    private TableCreator tableCreator;
    private DoneOrderDao doneOrderDao;
    private TicketDao ticketDao;
    private OrderDao orderDao;
    private List<DoneOrder> doneOrders = new ArrayList<>();
    private DoneOrder firstDoneOrder;
    private DoneOrder secondDoneOrder;

    @BeforeEach
    void prepareTable() {
        connector = new BDConnector(PROPERTIES);
        tableCreator = new TableCreator(connector);
        orderDao = new OrderDao(connector);
        ticketDao = new TicketDao(connector);
        doneOrderDao = new DoneOrderDao(connector);

        createTestData();
    }

    @Test
    void saveShouldAddDoneOrderToTheDatabase() {
        Ticket ticket = ticketDao.findById(1L).orElse(null);

        DoneOrder doneOrder = DoneOrder.builder()
                .id(3L)
                .ticket(ticket)
                .build();

        doneOrderDao.save(doneOrder);
        DoneOrder actual = doneOrderDao.findById(3L).get();

        assertEquals(doneOrder, actual);
    }

    @Test
    void findByIdShouldReturnDoneOrderWhenGetId() {
        DoneOrder actual = doneOrderDao.findById(1L).get();
        assertEquals(firstDoneOrder, actual);
    }

    @Test
    void findAllShouldReturnListOfDoneOrders() {
        List<DoneOrder> actual = doneOrderDao.findAll();
        assertEquals(doneOrders, actual);
    }

    @Test
    void updateShouldUpdateDoneOrder() {
        Ticket ticket = ticketDao.findById(2L).orElse(null);

        DoneOrder expected = DoneOrder.builder()
                .id(1L)
                .ticket(ticket)
                .build();

        doneOrderDao.update(expected);
        DoneOrder actual = doneOrderDao.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldDeleteDoneOrderWhenGetId() {
        doneOrderDao.deleteById(2L);
        DoneOrder actual = doneOrderDao.findById(2L).orElse(null);
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

       firstDoneOrder = DoneOrder.builder()
                .id(1L)
                .ticket(firstTicket)
                .build();

       secondDoneOrder = DoneOrder.builder()
                .id(2L)
                .ticket(secondTicket)
                .build();

        doneOrderDao.save(firstDoneOrder);
        doneOrderDao.save(secondDoneOrder);
        doneOrders.add(firstDoneOrder);
        doneOrders.add(secondDoneOrder);
    }
}
