//package by.it_academy.jd2.mk_jd2_92_22.dao;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.*;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TicketDaoTest {
//    private final static String PROPERTIES = "src/test/resources/h2.properties";
//    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";
//
//    private BDConnector connector;
//    private TableCreator tableCreator;
//    private TicketDao ticketDao;
//    private OrderDao orderDao;
//
//    private Ticket firstTicket;
//    private Ticket secondTicket;
//    private List<Ticket> tickets = new ArrayList<>();
//
//    @BeforeEach
//    void prepareTable() {
//        connector = new BDConnector(PROPERTIES);
//        tableCreator = new TableCreator(connector);
//        ticketDao = new TicketDao(connector);
//        orderDao = new OrderDao(connector);
//
//        createTestData();
//    }
//
//    @Test
//    void saveShouldAddTicketToTheDatabase() {
//        Order order = orderDao.findById(1L).orElse(null);
//
//        Ticket ticket = Ticket.builder()
//                .id(3L)
//                .order(order)
//                .build();
//
//        ticketDao.save(ticket);
//        Ticket actual = ticketDao.findById(3L).get();
//
//        assertEquals(ticket, actual);
//    }
//
//    @Test
//    void findByIdShouldReturnTicketWhenGetId() {
//        Ticket actual = ticketDao.findById(1L).get();
//        assertEquals(firstTicket, actual);
//    }
//
//    @Test
//    void findAllShouldReturnListOfTicket() {
//        List<Ticket> actual = ticketDao.findAll();
//        assertEquals(tickets, actual);
//    }
//
//    @Test
//    void updateShouldUpdateTicket() {
//        Order order = orderDao.findById(2L).orElse(null);
//
//        Ticket expected = Ticket.builder()
//                .id(1L)
//                .order(order)
//                .build();
//
//        ticketDao.update(expected);
//        Ticket actual = ticketDao.findById(1L).orElse(null);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteByIdShouldDeleteTicketWhenGetId() {
//        ticketDao.deleteById(2L);
//        Ticket actual = ticketDao.findById(2L).orElse(null);
//        assertThat(actual).isNull();
//    }
//    private void createTestData() {
//        tableCreator.runScript(SCRIPT_SQL);
//
//        Order firstOrder = Order.builder()
//                .id(1L)
//                .build();
//
//        Order secondOrder = Order.builder()
//                .id(2L)
//                .build();
//        orderDao.save(firstOrder);
//        orderDao.save(secondOrder);
//
//        firstTicket = Ticket.builder()
//                .id(1L)
//                .order(firstOrder)
//                .build();
//
//        secondTicket = Ticket.builder()
//                .id(2L)
//                .order(secondOrder)
//                .build();
//
//        ticketDao.save(firstTicket);
//        ticketDao.save(secondTicket);
//        tickets.add(firstTicket);
//        tickets.add(secondTicket);
//    }
//}