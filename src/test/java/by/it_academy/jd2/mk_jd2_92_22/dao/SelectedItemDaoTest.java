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
//public class SelectedItemDaoTest {
//    private final static String PROPERTIES = "src/test/resources/h2.properties";
//    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";
//
//    private BDConnector connector;
//    private TableCreator tableCreator;
//    private SelectedItemDao selectedItemDao;
//    private OrderDao orderDao;
//    private MenuRowDao menuRowDao;
//    private PizzaInfoDao pizzaInfoDao;
//    private List<SelectedItem> selectedItems = new ArrayList<>();
//    private SelectedItem firstSelectedItem;
//    private SelectedItem secondSelectedItem;
//
//    @BeforeEach
//    void prepareTable() {
//        connector = new BDConnector(PROPERTIES);
//        tableCreator = new TableCreator(connector);
//        selectedItemDao = new SelectedItemDao(connector);
//        orderDao = new OrderDao(connector);
//        pizzaInfoDao = new PizzaInfoDao(connector);
//        menuRowDao = new MenuRowDao(connector);
//
//        createTestData();
//    }
//
//    @Test
//    void saveShouldAddSelectedItemToTheDatabase() {
//       MenuRow menuRow = menuRowDao.findById(1L).orElse(null);
//       Order order = orderDao.findById(1L).orElse(null);
//
//        SelectedItem selectedItem = SelectedItem.builder()
//                .id(3L)
//                .menuRow(menuRow)
//                .order(order)
//                .count(4L)
//                .build();
//
//        selectedItemDao.save(selectedItem);
//        SelectedItem actual = selectedItemDao.findById(3L).get();
//
//        assertEquals(selectedItem, actual);
//    }
//
//    @Test
//    void findByIdShouldReturnSelectedItemWhenGetId() {
//        SelectedItem actual = selectedItemDao.findById(1L).get();
//        assertEquals(firstSelectedItem, actual);
//    }
//
//    @Test
//    void findAllShouldReturnListOfSelectedItem() {
//        List<SelectedItem> actual = selectedItemDao.findAll();
//        assertEquals(selectedItems, actual);
//    }
//
//    @Test
//    void updateShouldUpdateSelectedItem() {
//        MenuRow menuRow = menuRowDao.findById(1L).orElse(null);
//        Order order = orderDao.findById(1L).orElse(null);
//
//        SelectedItem expected = SelectedItem.builder()
//                .id(1L)
//                .menuRow(menuRow)
//                .order(order)
//                .count(6L)
//                .build();
//
//        selectedItemDao.update(expected);
//        SelectedItem actual = selectedItemDao.findById(1L).orElse(null);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteByIdShouldDeleteSelectedItemWhenGetId() {
//        selectedItemDao.deleteById(2L);
//        SelectedItem actual = selectedItemDao.findById(2L).orElse(null);
//        assertThat(actual).isNull();
//    }
//
//    @Test
//    void findAllSelectedItemsByOrderIdShouldReturnListOfMenuRows() {
//        List<SelectedItem> expected = new ArrayList<>();
//        MenuRow menuRow = menuRowDao.findById(1L).orElse(null);
//        Order order = orderDao.findById(1L).orElse(null);
//
//        expected.add(SelectedItem.builder()
//                .id(1L)
//                .menuRow(menuRow)
//                .order(order)
//                .count(4L)
//                .build());
//
//        List <SelectedItem> actual = selectedItemDao.findAllSelectedItemByIdOrder(1L);
//        assertEquals(expected, actual);
//    }
//
//    private void createTestData() {
//        tableCreator.runScript(SCRIPT_SQL);
//        PizzaInfo pizzaInfo1 = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(32L)
//                .build();
//
//        PizzaInfo pizzaInfo2 = PizzaInfo.builder()
//                .id(2L)
//                .name("4season")
//                .description("very testy")
//                .size(32L)
//                .build();
//
//        pizzaInfoDao.save(pizzaInfo1);
//        pizzaInfoDao.save(pizzaInfo2);
//
//
//        MenuRow firstMenuRow = MenuRow.builder()
//                .id(1L)
//                .price(50.2)
//                .pizzaInfo(pizzaInfo1)
//                .build();
//
//       MenuRow secondMenuRow = MenuRow.builder()
//                .id(2L)
//                .price(21.2)
//                .pizzaInfo(pizzaInfo2)
//                .build();
//        menuRowDao.save(firstMenuRow);
//        menuRowDao.save(secondMenuRow);
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
//        firstSelectedItem = SelectedItem.builder()
//                .id(1L)
//                .menuRow(firstMenuRow)
//                .order(firstOrder)
//                .count(4L)
//                .build();
//
//        secondSelectedItem = SelectedItem.builder()
//                .id(2L)
//                .menuRow(secondMenuRow)
//                .order(secondOrder)
//                .count(5L)
//                .build();
//
//        selectedItemDao.save(firstSelectedItem);
//        selectedItemDao.save(secondSelectedItem);
//        selectedItems.add(firstSelectedItem);
//        selectedItems.add(secondSelectedItem);
//    }
//}
