//package by.it_academy.jd2.mk_jd2_92_22.dao;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderDao;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.TableCreator;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class OrderDaoTest {
//    private final static String PROPERTIES = "src/test/resources/h2.properties";
//    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";
//
//    private BDConnector connector;
//    private TableCreator tableCreator;
//    private OrderDao orderDao;
//    private List<Order> orders = new ArrayList<>();
//    private Order firstOrder;
//    private Order secondOrder;
//
//    @BeforeEach
//    void prepareTable() {
//        connector = new BDConnector(PROPERTIES);
//        tableCreator = new TableCreator(connector);
//        orderDao = new OrderDao(connector);
//
//        createTestData();
//    }
//
//    @Test
//    void saveShouldAddOrderToTheDatabase() {
//        Order order = Order.builder()
//                .id(3L)
//                .build();
//
//        orderDao.save(order);
//        Order actual = orderDao.findById(3L).get();
//
//        assertEquals(order, actual);
//    }
//
//    @Test
//    void findByIdShouldReturnOrderWhenGetId() {
//        Order actual = orderDao.findById(1L).get();
//        assertEquals(firstOrder, actual);
//    }
//
//    @Test
//    void findAllShouldReturnListOfOrder() {
//        List<Order> actual = orderDao.findAll();
//        assertEquals(orders, actual);
//    }
//
//    @Test
//    void updateShouldUpdateOrder() {
//        Order expected = Order.builder()
//                .id(1L)
//                .build();
//
//        orderDao.update(expected);
//        Order actual = orderDao.findById(1L).orElse(null);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteByIdShouldDeleteOrderWhenGetId() {
//        orderDao.deleteById(2L);
//        Order actual = orderDao.findById(2L).orElse(null);
//        assertThat(actual).isNull();
//    }
//    private void createTestData() {
//        tableCreator.runScript(SCRIPT_SQL);
//
//        firstOrder = Order.builder()
//                .id(1L)
//                .build();
//
//        secondOrder = Order.builder()
//                .id(2L)
//                .build();
//
//        orderDao.save(firstOrder);
//        orderDao.save(secondOrder);
//        orders.add(firstOrder);
//        orders.add(secondOrder);
//    }
//}
