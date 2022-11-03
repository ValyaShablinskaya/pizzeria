package by.it_academy.jd2.mk_jd2_92_22.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.exception.DataBaseRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuRowDaoTest {
    private final static String PROPERTIES = "src/test/resources/h2.properties";
    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";

    private BDConnector connector;
    private TableCreator tableCreator;
    private MenuRowDao menuRowDao;
    private PizzaInfoDao pizzaInfoDao;
    private MenuDao menuDao;
    private MenuRow firstMenuRow;
    private MenuRow secondMenuRow;
    private List<MenuRow> menuRows = new ArrayList<>();
    private List<PizzaInfo> pizzaInfos = new ArrayList<>();

    @BeforeEach
    void prepareTable() {
        connector = new BDConnector(PROPERTIES);
        tableCreator = new TableCreator(connector);
        menuRowDao = new MenuRowDao(connector);
        pizzaInfoDao = new PizzaInfoDao(connector);
        menuDao = new MenuDao(connector);

        createTestData();
    }

    @Test
    void saveShouldAddMenuRowToTheDatabase() {

        PizzaInfo firstPizzaInfo = pizzaInfoDao.findById(1L).orElse(null);

        MenuRow menuRow = MenuRow.builder()
                .id(3L)
                .price(21.2)
                .pizzaInfo(firstPizzaInfo)
                .build();

        menuRowDao.save(menuRow);
        MenuRow actual = menuRowDao.findById(3L).get();

        assertEquals(menuRow, actual);
    }

    @Test
    void findByIdShouldReturnMenuRowWhenGetId() {
        MenuRow actual = menuRowDao.findById(1L).get();
        assertEquals(firstMenuRow, actual);
    }

    @Test
    void findAllShouldReturnListOfMenuRow() {
        List<MenuRow> actual = menuRowDao.findAll();
        assertEquals(menuRows, actual);
    }

    @Test
    void updateShouldUpdateMenuRow() {
        PizzaInfo firstPizzaInfo = pizzaInfoDao.findById(1L).orElse(null);

        MenuRow expected = MenuRow.builder()
                .id(1L)
                .price(21.2)
                .pizzaInfo(firstPizzaInfo)
                .build();

        menuRowDao.update(expected);
        MenuRow actual = menuRowDao.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldDeleteMenuRowWhenGetId() {
        menuRowDao.deleteById(2L);
        MenuRow actual = menuRowDao.findById(2L).orElse(null);
        assertThat(actual).isNull();
    }

    @Test
    void findAllMenuRowsByMenuIdShouldReturnListOfMenuRows() {
        List<MenuRow> expected = new ArrayList<>();

        PizzaInfo firstPizzaInfo = pizzaInfoDao.findById(1L).orElse(null);

       expected.add(MenuRow.builder()
                .id(1L)
                .price(50.2)
                .pizzaInfo(firstPizzaInfo)
                .build());

        List <MenuRow> actual = menuRowDao.findAllRowsByIdMenu(1L);
        assertEquals(expected, actual);
    }

    private void createTestData() {
        tableCreator.runScript(SCRIPT_SQL);

       PizzaInfo pizzaInfo1 = PizzaInfo.builder()
               .id(1L)
               .name("Margarita")
               .description("testy")
               .size(32L)
               .build();

       PizzaInfo pizzaInfo2 = PizzaInfo.builder()
               .id(2L)
               .name("4season")
               .description("very testy")
               .size(32L)
               .build();

       pizzaInfoDao.save(pizzaInfo1);
       pizzaInfoDao.save(pizzaInfo2);
       pizzaInfos.add(pizzaInfo1);
       pizzaInfos.add(pizzaInfo2);

        firstMenuRow = MenuRow.builder()
                .id(1L)
                .price(50.2)
                .pizzaInfo(pizzaInfo1)
                .build();

        secondMenuRow = MenuRow.builder()
                .id(2L)
                .price(21.2)
                .pizzaInfo(pizzaInfo2)
                .build();

        menuRowDao.save(firstMenuRow);
        menuRowDao.save(secondMenuRow);
        menuRows.add(firstMenuRow);
        menuRows.add(secondMenuRow);

        Menu firstMenu = Menu.builder()
                .id(1L)
                .name("Summer")
                .enabled(true)
                .build();

        menuDao.save(firstMenu);
        menuRowDao.addRowOnMenu(1L, 1L);
        //menuRowDao.addRowOnMenu(menuDao.findById(1L).get().getId(), menuRowDao.findById(2L).get().getId());
    }
}
