//package by.it_academy.jd2.mk_jd2_92_22.dao;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.*;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class MenuDaoTest {
//    private final static String PROPERTIES = "src/test/resources/h2.properties";
//    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";
//
//    private BDConnector connector;
//    private TableCreator tableCreator;
//    private MenuDao menuDao;
//    private List<Menu> menus = new ArrayList<>();
//    private Menu firstMenu;
//    private Menu secondMenu;
//
//    @BeforeEach
//    void prepareTable() {
//        connector = new BDConnector(PROPERTIES);
//        tableCreator = new TableCreator(connector);
//        menuDao = new MenuDao(connector);
//
//        createTestData();
//    }
//
//    @Test
//    void saveShouldAddMenuToTheDatabase() {
//        Menu menu = Menu.builder()
//                .id(3L)
//                .name("Winter")
//                .enabled(true)
//                .build();
//
//        menuDao.save(menu);
//        Menu actual = menuDao.findById(3L).get();
//
//        assertEquals(menu, actual);
//    }
//
//    @Test
//    void findByIdShouldReturnMenuWhenGetId() {
//        Menu actual = menuDao.findById(1L).get();
//        assertEquals(firstMenu, actual);
//    }
//
//    @Test
//    void findAllShouldReturnListOfMenu() {
//        List<Menu> actual = menuDao.findAll();
//        assertEquals(menus, actual);
//    }
//
//    @Test
//    void updateShouldUpdateMenuRow() {
//        Menu expected = Menu.builder()
//                .id(1L)
//                .name("Autumn")
//                .enabled(true)
//                .build();
//
//        menuDao.update(expected);
//        Menu actual = menuDao.findById(1L).orElse(null);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteByIdShouldDeleteMenuRowWhenGetId() {
//        menuDao.deleteById(2L);
//        Menu actual = menuDao.findById(2L).orElse(null);
//        assertThat(actual).isNull();
//    }
//
//    private void createTestData() {
//        tableCreator.runScript(SCRIPT_SQL);
//
//        firstMenu = Menu.builder()
//                .id(1L)
//                .name("Summer")
//                .enabled(true)
//                .build();
//
//        secondMenu = Menu.builder()
//                .id(2L)
//                .name("Weekend")
//                .enabled(true)
//                .build();
//
//        menuDao.save(firstMenu);
//        menuDao.save(secondMenu);
//        menus.add(firstMenu);
//        menus.add(secondMenu);
//    }
//}
