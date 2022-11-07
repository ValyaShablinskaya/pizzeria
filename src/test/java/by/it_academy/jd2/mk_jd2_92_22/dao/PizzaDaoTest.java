package by.it_academy.jd2.mk_jd2_92_22.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzaDaoTest {
    private final static String PROPERTIES = "src/test/resources/h2.properties";
    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";

    private BDConnector connector;
    private TableCreator tableCreator;
    private PizzaDao pizzaDao;
    private TicketDao ticketDao;
    private OrderDao orderDao;
    private DoneOrderDao doneOrderDao;

    private List<Pizza> pizzas = new ArrayList<>();
    private Pizza firstPizza;
    private Pizza secondPizza;

    @BeforeEach
    void prepareTable() {
        connector = new BDConnector(PROPERTIES);
        tableCreator = new TableCreator(connector);
        pizzaDao = new PizzaDao(connector);
        orderDao = new OrderDao(connector);
        ticketDao = new TicketDao(connector);
        doneOrderDao = new DoneOrderDao(connector);

        createTestData();
    }

    @Test
    void saveShouldAddPizzaToTheDatabase() {
        DoneOrder doneOrder = doneOrderDao.findById(1L).orElse(null);

        Pizza pizza = Pizza.builder()
                .id(3L)
                .name("cheese")
                .size(25L)
                .doneOrder(doneOrder)
                .build();

        pizzaDao.save(pizza);
        Pizza actual = pizzaDao.findById(3L).get();

        assertEquals(pizza, actual);
    }

    @Test
    void findByIdShouldReturnPizzaWhenGetId() {
        Pizza actual = pizzaDao.findById(1L).get();
        assertEquals(firstPizza, actual);
    }

    @Test
    void findAllShouldReturnListOfPizza() {
        List<Pizza> actual = pizzaDao.findAll();
        assertEquals(pizzas, actual);
    }

    @Test
    void updateShouldUpdatePizza() {
        DoneOrder doneOrder = doneOrderDao.findById(1L).orElse(null);

        Pizza expected = Pizza.builder()
                .id(1L)
                .name("Margarita")
                .size(25L)
                .doneOrder(doneOrder)
                .build();

        pizzaDao.update(expected);
        Pizza actual = pizzaDao.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldDeletePizzaWhenGetId() {
        pizzaDao.deleteById(2L);
        Pizza actual = pizzaDao.findById(2L).orElse(null);
        assertThat(actual).isNull();
    }

    @Test
    void findAllPizzasByDoneOrderIdShouldReturnListOfMenuRows() {
        List<Pizza> expected = new ArrayList<>();
        DoneOrder doneOrder = doneOrderDao.findById(1L).orElse(null);

        expected.add(Pizza.builder()
                .id(1L)
                .name("Margarita")
                .size(25L)
                .doneOrder(doneOrder)
                .build());

        List <Pizza> actual = pizzaDao.findAllPizzasByIdDoneOrder(1L);
        assertEquals(expected, actual);
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

        DoneOrder firstDoneOrder = DoneOrder.builder()
                .id(1L)
                .ticket(firstTicket)
                .build();

        DoneOrder secondDoneOrder = DoneOrder.builder()
                .id(2L)
                .ticket(secondTicket)
                .build();

        doneOrderDao.save(firstDoneOrder);
        doneOrderDao.save(secondDoneOrder);

        firstPizza = Pizza.builder()
                .id(1L)
                .name("Margarita")
                .size(25L)
                .doneOrder(firstDoneOrder)
                .build();

        secondPizza = Pizza.builder()
                .id(2L)
                .name("Four seasons")
                .size(30L)
                .doneOrder(secondDoneOrder)
                .build();

        pizzaDao.save(firstPizza);
        pizzaDao.save(secondPizza);
        pizzas.add(firstPizza);
        pizzas.add(secondPizza);
    }
}
